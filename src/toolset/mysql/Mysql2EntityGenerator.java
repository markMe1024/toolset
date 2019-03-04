package toolset.mysql;

import static toolset.string.StringUtil.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @Description 数据库表映射实体类
 * @Author From Internet
 * @Date 2018年12月6日
 */
public class Mysql2EntityGenerator {
    // 数据库信息
    private static final String URL ="jdbc:mysql://222.132.155.200:3306/mhealth?useUnicode=true&characterEncoding=utf-8";
    private static final String NAME = "root";
    private static final String PASSWORD = "jkl2014";
    
    // 指定实体生成所在包的路径
    private String packageOutPath;
    // 表名
    private String tablename;
    // 列名数组
    private String[] colNames; 
    // 列类型数组
    private String[] colTypes; 
    // 列名大小数组
    private int[] colSizes; 
    // 是否需要导入包java.util.*
    private boolean iUtil = false;
    // 是否需要导入包java.sql.*
    private boolean iSql = false; 
    // 是否需要导入uuid生成id的注解
    private boolean iIdVarchar = false;
    // 是否需要导入生成自增id的注解
    private boolean iIdInt = false;
    // 是否需要导入date、time时间类型字段注解
    private boolean iTime = false;
    // 是否需要导入datetime时间类型字段注解
    private boolean iDateTime = false;
    
    /**
     * @Description main
     * @Author From Internet
     * @Date 2018年12月6日
     * @Note 使用时替换创建对象时的第二个第三个参数，分别是表名和实体类目标路径
     */
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(URL,NAME,PASSWORD);
            new Mysql2EntityGenerator(connection,"t_health_operation_record", "com.dhcc.mhealth.entity.healthy.files.operation.record");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description 构造函数
     * @Author From Internet
     * @Date 2018年12月6日
     */
    public Mysql2EntityGenerator(Connection connection,String tableName, String packageOutPath){
        
        // 1.初始化成员变量
        this.tablename = tableName;
        this.packageOutPath = packageOutPath;
        
        try {
            // 2.查询table信息 
            String sql = "select * from " + tablename;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSetMetaData resultSetMetaData = preparedStatement.getMetaData();
            int size = resultSetMetaData.getColumnCount();   
            colNames = new String[size];
            colTypes = new String[size];
            colSizes = new int[size];
            for (int i = 0; i < size; i++) {
                colNames[i] = resultSetMetaData.getColumnName(i + 1);
                colTypes[i] = resultSetMetaData.getColumnTypeName(i + 1);
                colSizes[i] = resultSetMetaData.getColumnDisplaySize(i + 1);
                
                if (colTypes[i].equalsIgnoreCase("datetime")) {
                    iUtil = true;
                }
                
                if (colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")) {
                    iSql = true;
                }
                
                if (colTypes[i].equalsIgnoreCase("date") || colTypes[i].equalsIgnoreCase("time")) {
                    iTime = true;
                }
                
                if (colTypes[i].equalsIgnoreCase("datetime")) {
                    iDateTime = true;
                }
                
                if ("id".equals(colNames[i]) && "varchar".equalsIgnoreCase(colTypes[i])) {
                    iIdVarchar = true;
                }
                
                if ("id".equals(colNames[i]) && "int".equalsIgnoreCase(colTypes[i])) {
                    iIdInt = true;
                }
            }
            
            // 3.生成实体类主体代码
            String content = parse(colNames, colTypes, colSizes);
            
            // 4.
            try {
                File directory = new File("");
                String outputPath = directory.getAbsolutePath() + "\\src\\main\\java\\" + this.packageOutPath.replace(".", "\\") + "\\"+parseName(tablename) + ".java";
                
                // 4.1.创建包
                String packagePath = directory.getAbsolutePath() + "\\src\\main\\java\\" + this.packageOutPath.replace(".", "\\");
                File file = new File(packagePath);
                Boolean mkdirSuccess = file.mkdirs();
                System.out.println("创建实体类所在包成功? " + mkdirSuccess);
                
                FileWriter fileWriter = new FileWriter(outputPath);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println(content);
                printWriter.flush();    
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
        }
    }

    /**
     * @Description 生成实体类主体代码
     * @Author From Internet
     * @Date 2018年12月6日
     */
    private String parse(String[] colNames, String[] colTypes, int[] colSizes) {
        StringBuffer entityStr = new StringBuffer();

        // 1.package声明
        entityStr.append("package " + this.packageOutPath + ";\r\n");
        entityStr.append("\r\n");

        // 2.import部分
        if (iUtil) {
            entityStr.append("import java.util.Date;\r\n");
            entityStr.append("\r\n");
        }
        if (iSql) {
            entityStr.append("import java.sql.*;\r\n");
            entityStr.append("\r\n");
        }
        if (iIdVarchar) {
            entityStr.append("import org.hibernate.annotations.GenericGenerator;\r\n");
            entityStr.append("\r\n");
        }
        if (iTime) {
            entityStr.append("import com.fasterxml.jackson.annotation.JsonFormat;\r\n");
            entityStr.append("\r\n");
            entityStr.append("import javax.persistence.Temporal;\r\n");
            entityStr.append("import javax.persistence.TemporalType;\r\n");
        }
        if (iDateTime) {
            entityStr.append("import javax.persistence.Temporal;\r\n");
            entityStr.append("import javax.persistence.TemporalType;\r\n");
        }
        if (iIdVarchar) {
            entityStr.append("import javax.persistence.GeneratedValue;\r\n");
        }
        if (iIdInt) {
            entityStr.append("import javax.persistence.GeneratedValue;\r\n");
            entityStr.append("import javax.persistence.GenerationType;\r\n");
        }
        entityStr.append("import javax.persistence.Entity;\r\n");
        entityStr.append("import javax.persistence.Table;\r\n");
        entityStr.append("import javax.persistence.Column;\r\n");
        entityStr.append("import javax.persistence.Id;\r\n");
        entityStr.append("\r\n");
        entityStr.append("import java.io.Serializable;\r\n");
        entityStr.append("\r\n");
        
        // 3.实体部分
        entityStr.append("@Entity\r\n");
        entityStr.append("@Table(name = \""+tablename+"\")\r\n");
        entityStr.append("public class " + parseName(tablename) + " implements Serializable {\r\n");
        // 属性
        processAllAttrs(entityStr);
        // get set方法
        processAllMethod(entityStr);
        entityStr.append("}\r\n");
        
        return entityStr.toString();
    }
    
    /**
     * @Description 数据库表名转驼峰(首字母大写)
     * @Author From Internet
     * @Date 2018年12月6日
     */
    private String parseName(String name){
        StringBuffer result = new StringBuffer();
        if (!nullOrEmpty(name)) {
            String[] array = name.split("_");
            if (array.length > 0){
                for (String tmp : array) {
                    if ("t".equalsIgnoreCase(tmp)) {
                        continue;
                    }
                    result.append(initCap(tmp));
                }
            }
        }
        return result.toString();
    }
    
    /**
     * @Description 将输入字符串的首字母改成大写
     * @Author From Internet
     * @Date 2018年12月6日
     */
    private String initCap(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z'){
            ch[0] = (char)(ch[0] - 32);
        }
        return new String(ch);
    }
    
    /**
     * @Description 生成所有属性
     * @Author From Internet
     * @Date 2018年12月6日
     */
    private void processAllAttrs(StringBuffer entityStr) {
        for (int i = 0; i < colNames.length; i++) {
            // 属性名转为驼峰命名(首字母小写)
            String column = parseNameNotFirst(colNames[i]);
            entityStr.append("\r\n");
            
            if ("id".equals(column)) {
                if ("varchar".equalsIgnoreCase(colTypes[i])) {
                    entityStr.append("\t@Id\r\n");
                    entityStr.append("\t@GenericGenerator(name = \"idGenerator\", strategy = \"uuid\")\r\n");
                    entityStr.append("\t@GeneratedValue(generator = \"idGenerator\")\r\n");
                    entityStr.append("\t@Column(name = \"" + colNames[i] + "\")\r\n");
                }else {
                    entityStr.append("\t@Id\r\n");
                    entityStr.append("\t@Column(name = \"" + colNames[i] + "\")\r\n");
                    entityStr.append("\t@GeneratedValue(strategy = GenerationType.IDENTITY)\r\n");
                }
            }else {
                if ("datetime".equalsIgnoreCase(colTypes[i])) {
                    entityStr.append("\t@Temporal(TemporalType.TIMESTAMP)\r\n");
                }
                
                if ("date".equalsIgnoreCase(colTypes[i])) {
                    entityStr.append("\t@JsonFormat(pattern=\"yyyy-MM-dd\", timezone=\"GMT+8\")\r\n");
                }
                
                if ("time".equalsIgnoreCase(colTypes[i])) {
                    entityStr.append("\t@JsonFormat(pattern=\"HH:mm:ss\", timezone=\"GMT+8\"))\r\n");
                }
                
                entityStr.append("\t@Column(name = \"" + colNames[i] + "\")\r\n");
            }
            
            entityStr.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " " + column + ";\r\n");
        }
    }
    
    /**
     * @Description 属性名转为驼峰命名(首字母小写)
     * @Author From Internet
     * @Date 2018年12月6日
     */
    private String parseNameNotFirst(String name){
        StringBuffer result = new StringBuffer();
        if (!nullOrEmpty(name)) {
            String[] array = name.split("_");
            if (array.length > 0){
                for (int i = 0; i < array.length; i++) {
                    if (i > 0) {
                        result.append(initCap(array[i]));
                    }else{
                        result.append(array[i]);
                    }
                }
            }
        }
        return result.toString();
    }

    /**
     * @Description 生成get、set方法
     * @Author From Internet
     * @Date 2018年12月6日
     */
    private void processAllMethod(StringBuffer entityStr) {
        for (int i = 0; i < colNames.length; i++) {
            entityStr.append("\r\n");
            entityStr.append("\tpublic void set" + parseName(colNames[i]) + "(" + sqlType2JavaType(colTypes[i]) + " " +  parseNameNotFirst(colNames[i]) + "){\r\n");
            entityStr.append("\t\tthis." + parseNameNotFirst(colNames[i]) + " = " + parseNameNotFirst(colNames[i]) + ";\r\n");
            entityStr.append("\t}\r\n");
            entityStr.append("\r\n");
            entityStr.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + parseName(colNames[i]) + "(){\r\n");
            entityStr.append("\t\treturn " + parseNameNotFirst(colNames[i]) + ";\r\n");
            entityStr.append("\t}\r\n");
        }
    }
    
    /**
     * @Description sql数据类型转java数据类型
     * @Author From Internet
     * @Date 2018年12月6日
     */
    private String sqlType2JavaType(String sqlType) {
        if (sqlType.equalsIgnoreCase("bit")) {
            return "Boolean";
        }else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "Byte";
        }else if (sqlType.equalsIgnoreCase("smallint")) {
            return "Short";
        }else if (sqlType.equalsIgnoreCase("int") || sqlType.equalsIgnoreCase("INT UNSIGNED")) {
            return "Integer";
        }else if (sqlType.equalsIgnoreCase("bigint")) {
            return "Long";
        }else if (sqlType.equalsIgnoreCase("float")) {
            return "Float";
        }else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric") || sqlType.equalsIgnoreCase("real")  
                || sqlType.equalsIgnoreCase("money") || sqlType.equalsIgnoreCase("smallmoney")){
            return "Double";
        }else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char") || sqlType.equalsIgnoreCase("nvarchar")  
                || sqlType.equalsIgnoreCase("nchar") || sqlType.equalsIgnoreCase("text")){
            return "String";
        }else if (sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("date") || sqlType.equalsIgnoreCase("time")){
            return "Date";
        }else if (sqlType.equalsIgnoreCase("image")){
            return "Blod";
        }
        return null;
    }
}
