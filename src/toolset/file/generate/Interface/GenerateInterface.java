package toolset.file.generate.Interface;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 创建新建一个接口需要的package和class
 * @Author .Mark
 * @Date 2019年1月12日
 * @Note 包访问路径常量需要替换成自己本地路径
 * @Note 包名替换为自己想要建的包的名字或者已存在的包名
 * @Note 类名替换为自己想要建的类的名字
 * @Note 作者替换自己名
 */
public class GenerateInterface {    
    /**
     * Dao层包访问路径
     */
    private static String DAO_DIR = "D:\\development\\workspace\\jklApi\\src\\main\\java\\com\\dhcc\\mhealth\\dao\\";
    
    /**
     * Service层包访问路径
     */
    private static String SERVICE_DIR = "D:\\development\\workspace\\jklApi\\src\\main\\java\\com\\dhcc\\mhealth\\service\\";
    
    /**
     * Blh层包访问路径
     */
    private static String BLH_DIR = "D:\\development\\workspace\\jklApi\\src\\main\\java\\com\\dhcc\\mhealth\\blh\\";
    
    /**
     * Resource层包访问路径
     */
    private static String RESOURCE_DIR = "D:\\development\\workspace\\jklApi\\src\\main\\java\\com\\dhcc\\mhealth\\web\\jklapi\\";
    
    /**
     * 类所在包的包名（这个包可以是要去新建的，也可以是已存在的）
     */
    private static String PACKAGE_NAME = "health\\chain";
    
    /**
     * 类名
     */
    private static String CLASS_NAME = "HealthChain";
    
    /**
     * 类注释
     */
    private static String CLASS_ANNOTATION = "供健康链调用的接口";
    
    /**
     * 作者
     */
    private static String AUTHOR = ".Mark";
    
    public static void main(String[] args) {
        // 1.创建新建一个Dao需要的Package和Class
        createDao();
        
        // 2.创建新建一个Service需要的Package和Interface
        createService();
        
        // 3.创建新建一个ServiceImpl需要的Package和Class
        createServiceImpl();
        
        // 4.创建新建一个Blh需要的Package和Class
        createBlh();
        
        // 5.创建新建一个Resource需要的Package和Class
        createResource();
    }
    
    /**
     * @Description 生成注释
     * @Author .Mark
     * @Date 2019年1月13日
     */
    private static StringBuffer generateAnnotation(StringBuffer code) {
        code.append("/**").append("\r\n");
        code.append(" * @Description ").append(CLASS_ANNOTATION).append("\r\n");
        code.append(" * @Author ").append(AUTHOR).append("\r\n");
        code.append(" * @Date ").append(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())).append("\r\n");
        code.append(" */").append("\r\n");
        return code;
    }
    
    /**
     * @Description 代码输出为文件
     * @Author .Mark
     * @Date 2019年1月12日
     */
    private static void outputAsAFile(String code, String outputPath) {
        try {
            FileWriter fileWriter = new FileWriter(outputPath);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(code);
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @Description 首字母小写
     * @Author .Mark
     * @Date 2019年1月13日
     */
    private static String makeTheFirstLetterLowercase(String word) {
        // 1.限制入参
        if (word == null || "".equals(word)) {
            return word;
        }
        
        // 2.首字母小写
        String firstLetter = String.valueOf(word.charAt(0));
        return word.replaceFirst(firstLetter, firstLetter.toLowerCase());
    }
    
    /**
     * @Description 创建新建一个Dao需要的Package和Class
     * @Author .Mark
     * @Date 2019年1月12日
     */
    private static void createDao() {
        // 1.创建目录
        String daoPath = DAO_DIR + PACKAGE_NAME;
        File file2CreatePackage = new File(daoPath);
        file2CreatePackage.mkdirs();
        System.out.println("创建Dao层Package完毕");
        
        // 2.生成dao类内代码
        String daoCode = generateDaoCode();
        
        // 3.代码输出为文件
        String outputPath = DAO_DIR + PACKAGE_NAME + "\\" + CLASS_NAME + "Dao.java";
        outputAsAFile(daoCode, outputPath);
        System.out.println("创建Dao完毕");
    }
    
    /**
     * @Description 生成Dao类内代码
     * @Author .Mark
     * @Date 2019年1月12日
     */
    private static String generateDaoCode() {
        // 1.
        StringBuffer daoCode = new StringBuffer();
        
        // 2.Package声明
        daoCode.append("package ").append("com.dhcc.mhealth.dao.").append(PACKAGE_NAME.replace("\\", ".")).append(";\r\n").append("\r\n");
        
        // 3.Import
        daoCode.append("import java.io.Serializable;").append("\r\n").append("\r\n");
        daoCode.append("import javax.annotation.Resource;").append("\r\n").append("\r\n");
        daoCode.append("import org.springframework.jdbc.core.JdbcTemplate;").append("\r\n");
        daoCode.append("import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;").append("\r\n");
        daoCode.append("import org.springframework.stereotype.Repository;").append("\r\n").append("\r\n");
        daoCode.append("import com.dhcc.framework.hibernate.dao.HibernatePersistentObjectDAO;").append("\r\n").append("\r\n");
        
        // 4.类注释
        daoCode = generateAnnotation(daoCode);
        
        // 5.类主体
        daoCode.append("@Repository").append("\r\n");
        daoCode.append("public class ").append(CLASS_NAME).append("Dao extends HibernatePersistentObjectDAO<Serializable> {").append("\r\n").append("\r\n");
        daoCode.append("    @Resource").append("\r\n");
        daoCode.append("    private JdbcTemplate jdbcTemplate;").append("\r\n").append("\r\n");
        daoCode.append("    @Resource").append("\r\n");
        daoCode.append("    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;").append("\r\n").append("\r\n");
        daoCode.append("}");
        
        return daoCode.toString();
    }
    
    /**
     * @Description 创建新建一个Service需要的Package和Interface
     * @Author .Mark
     * @Date 2019年1月12日
     */
    private static void createService() {
        // 1.创建目录
        String servicePath = SERVICE_DIR + PACKAGE_NAME;
        File file2CreatePackage = new File(servicePath);
        file2CreatePackage.mkdirs();
        System.out.println("创建Service层Package完毕");
        
        // 2.生成service接口内代码
        String serviceCode = generateServiceCode();
        
        // 3.代码输出为文件
        String outputPath = SERVICE_DIR + PACKAGE_NAME + "\\" + CLASS_NAME + "Service.java";
        outputAsAFile(serviceCode, outputPath);
        System.out.println("创建Service完毕");
    }
    
    /**
     * @Description 生成Service接口内代码
     * @Author .Mark
     * @Date 2019年1月12日
     */
    private static String generateServiceCode() {
        // 1.
        StringBuffer serviceCode = new StringBuffer();
        
        // 2.Package声明
        serviceCode.append("package ").append("com.dhcc.mhealth.service.").append(PACKAGE_NAME.replace("\\", ".")).append(";\r\n").append("\r\n");
        
        // 3.接口注释
        serviceCode = generateAnnotation(serviceCode);
        
        // 4.类主体
        serviceCode.append("public interface ").append(CLASS_NAME).append("Service {").append("\r\n").append("\r\n");
        serviceCode.append("}");
        
        return serviceCode.toString();
    }
    
    /**
     * @Description 创建新建一个ServiceImpl需要的Package和Class
     * @Author .Mark
     * @Date 2019年1月12日
     */
    private static void createServiceImpl() {
        // 1.创建目录
        String serviceImplPath = SERVICE_DIR + PACKAGE_NAME + "\\impl";
        File file2CreatePackage = new File(serviceImplPath);
        file2CreatePackage.mkdirs();
        System.out.println("创建Service层Package下的Impl包完毕");
        
        // 2.生成ServiceImpl类内代码
        String serviceImplCode = generateServiceImplCode();
        
        // 3.代码输出为文件
        String outputPath = SERVICE_DIR + PACKAGE_NAME + "\\impl\\" + CLASS_NAME + "ServiceImpl.java";
        outputAsAFile(serviceImplCode, outputPath);
        System.out.println("创建ServiceImpl完毕");
    }
    
    /**
     * @Description 生成serviceImpl类内代码
     * @Author .Mark
     * @Date 2019年1月12日
     */
    private static String generateServiceImplCode() {
        // 1.
        StringBuffer serviceImplCode = new StringBuffer();
        
        // 2.Package声明
        serviceImplCode.append("package ").append("com.dhcc.mhealth.service.").append(PACKAGE_NAME.replace("\\", ".")).append(".impl").append(";\r\n").append("\r\n");
        
        // 3.Import
        serviceImplCode.append("import javax.annotation.Resource;").append("\r\n").append("\r\n");
        serviceImplCode.append("import org.springframework.stereotype.Service;").append("\r\n").append("\r\n");
        serviceImplCode.append("import com.dhcc.mhealth.dao.").append(PACKAGE_NAME.replace("\\", ".")).append(".").append(CLASS_NAME).append("Dao;").append("\r\n");
        serviceImplCode.append("import com.dhcc.mhealth.service.").append(PACKAGE_NAME.replace("\\", ".")).append(".").append(CLASS_NAME).append("Service;").append("\r\n").append("\r\n");
        
        // 4.类注释m
        serviceImplCode = generateAnnotation(serviceImplCode);
        
        // 5.类主体
        serviceImplCode.append("@Service(\"").append(makeTheFirstLetterLowercase(CLASS_NAME)).append("Service\")").append("\r\n");
        serviceImplCode.append("public class ").append(CLASS_NAME).append("ServiceImpl implements ").append(CLASS_NAME).append("Service {").append("\r\n").append("\r\n");
        serviceImplCode.append("    @Resource").append("\r\n");
        serviceImplCode.append("    private ").append(CLASS_NAME).append("Dao ").append(makeTheFirstLetterLowercase(CLASS_NAME)).append("Dao;").append("\r\n").append("\r\n");
        serviceImplCode.append("}");
        
        return serviceImplCode.toString();
    }
    
    /**
     * @Description 创建新建一个Blh需要的Package和Class
     * @Author .Mark
     * @Date 2019年1月12日
     */
    private static void createBlh() {
        // 1.创建目录
        String blhPath = BLH_DIR + PACKAGE_NAME;
        File file2CreatePackage = new File(blhPath);
        file2CreatePackage.mkdirs();
        System.out.println("创建Blh层Package完毕");
        
        // 2.生成Blh类内代码
        String blhCode = generateBlhCode();
        
        // 3.代码输出为文件
        String outputPath = BLH_DIR + PACKAGE_NAME + "\\" + CLASS_NAME + "Blh.java";
        outputAsAFile(blhCode, outputPath);
        System.out.println("创建Blh完毕");
    }
    
    /**
     * @Description 生成Blh类内代码
     * @Author .Mark
     * @Date 2019年1月12日
     */
    private static String generateBlhCode() {
        // 1.
        StringBuffer blhCode = new StringBuffer();
        
        // 2.Package声明
        blhCode.append("package ").append("com.dhcc.mhealth.blh.").append(PACKAGE_NAME.replace("\\", ".")).append(";\r\n").append("\r\n");
        
        // 3.Import
        blhCode.append("import javax.annotation.Resource;").append("\r\n").append("\r\n");
        blhCode.append("import org.springframework.stereotype.Component;").append("\r\n").append("\r\n");
        blhCode.append("import com.dhcc.framework.app.blh.BaseAbstractBlh;").append("\r\n").append("\r\n");
        blhCode.append("import com.dhcc.mhealth.dto.BaseDhccDto;").append("\r\n");
        blhCode.append("import com.dhcc.mhealth.service.").append(PACKAGE_NAME.replace("\\", ".")).append(".").append(CLASS_NAME).append("Service;").append("\r\n").append("\r\n");
        
        // 4.类注释
        blhCode = generateAnnotation(blhCode);
        
        // 5.类主体
        blhCode.append("@Component").append("\r\n");
        blhCode.append("public class ").append(CLASS_NAME).append("Blh extends BaseAbstractBlh<BaseDhccDto> {").append("\r\n").append("\r\n");
        blhCode.append("    @Resource").append("\r\n");
        blhCode.append("    private ").append(CLASS_NAME).append("Service ").append(makeTheFirstLetterLowercase(CLASS_NAME)).append("Service;").append("\r\n").append("\r\n");
        blhCode.append("}");
        
        return blhCode.toString();
    }

    /**
     * @Description 创建新建一个Resource需要的Package和Class
     * @Author .Mark
     * @Date 2019年1月12日
     */
    private static void createResource() {
        // 1.创建目录
        String resourcePath = RESOURCE_DIR + PACKAGE_NAME;
        File file2CreatePackage = new File(resourcePath);
        file2CreatePackage.mkdirs();
        System.out.println("创建Resource层Package完毕");
        
        // 2.生成Resource类内代码
        String resourceCode = generateResourceCode();
        
        // 3.代码输出为文件
        String outputPath = RESOURCE_DIR  + PACKAGE_NAME + "\\" + CLASS_NAME + "Resource.java";
        outputAsAFile(resourceCode, outputPath);
        System.out.println("创建Resource完毕");
    }
    
    /**
     * @Description 生成Resource类内代码
     * @Author .Mark
     * @Date 2019年1月12日
     */
    private static String generateResourceCode() {
        // 1.
        StringBuffer resourceCode = new StringBuffer();
        
        // 2.Package声明
        resourceCode.append("package ").append("com.dhcc.mhealth.web.jklapi.").append(PACKAGE_NAME.replace("\\", ".")).append(";\r\n").append("\r\n");
        
        // 3.Import
        resourceCode.append("import javax.annotation.Resource;").append("\r\n").append("\r\n");
        resourceCode.append("import javax.ws.rs.Path;").append("\r\n").append("\r\n");
        resourceCode.append("import org.apache.commons.logging.Log;").append("\r\n");
        resourceCode.append("import org.apache.commons.logging.LogFactory;").append("\r\n").append("\r\n");
        resourceCode.append("import org.springframework.stereotype.Component;").append("\r\n").append("\r\n");
        resourceCode.append("import com.dhcc.mhealth.blh.").append(PACKAGE_NAME.replace("\\", ".")).append(".").append(CLASS_NAME).append("Blh;").append("\r\n").append("\r\n");
        
        // 4.类注释
        resourceCode = generateAnnotation(resourceCode);
        
        // 5.类主体
        resourceCode.append("@Component").append("\r\n");
        resourceCode.append("@Path(\"/").append(makeTheFirstLetterLowercase(CLASS_NAME)).append("\")").append("\r\n");
        resourceCode.append("public class ").append(CLASS_NAME).append("Resource {").append("\r\n").append("\r\n");
        resourceCode.append("    private static Log logger = LogFactory.getLog(").append(CLASS_NAME).append("Resource.class);").append("\r\n").append("\r\n");
        resourceCode.append("    @Resource").append("\r\n");
        resourceCode.append("    private ").append(CLASS_NAME).append("Blh ").append(makeTheFirstLetterLowercase(CLASS_NAME)).append("Blh;").append("\r\n").append("\r\n");
        resourceCode.append("}");
        
        return resourceCode.toString();
    }
}
