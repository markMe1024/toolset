package toolset.count.code.quantity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 统计代码量
 * @Author .Mark
 * @Date 2019年2月1日
 */
public class CountCodeQuantity {
    
    /**
     * 作者是.Mark
     */
    private static final String AUTHOR_MARK = "@Author.Mark";
    
    /**
     * 方法是2018年写的
     */
    private static final String DATE_2018 = "@Date2018";
    
    /**
     * 统计所有Java文件的基础文件夹
     */
    private static final String ROOT_PACKAGE = "D:\\development\\workspace\\jklApi";
    
    /**
     * 基础文件夹下所有Java文件
     */
    private List<File> allFiles = new ArrayList<>();
    
	public static void main(String[] args){
	    int sum = 0;
	    CountCodeQuantity countCodeQuantity = new CountCodeQuantity();
	    // 1.拿到目录下所有Java文件
	    List<File> allFiles = countCodeQuantity.getAllFile(ROOT_PACKAGE);
	    if (allFiles != null && allFiles.size() != 0) {
	        for (File file : allFiles) {
	            // 2.统计单个Java文件内代码行数
	            int linesOfCode = countCodeQuantity.countLinesOfCode(file);

	            // 3.单个文件内代码行数加到总代码行数上
	            sum += linesOfCode;
	        }
	    }
	    
	    System.err.println("总代码行数：" + sum);
	}
	
	/**
	 * @Description 拿到目录下所有Java文件
	 * @Author .Mark
	 * @Date 2019年2月1日
	 */
	private List<File> getAllFile(String path) {
	    // 1.读入文件夹
	    File file = new File(path);
	    
	    // 2.拿到文件夹的子目录
	    File[] files = file.listFiles();
	    
	    // 3.遍历这个子目录，判断是否是Java文件
	    if (files != null && files.length != 0) {
	        for (File tmp : files) {
	            // 3.1.是文件，则这个文件是我需要的
	            if (tmp.isFile() && tmp.getPath().endsWith(".java")) {
	                allFiles.add(tmp);
	            } 
	            // 3.2.如果是目录，递归调用2到4
	            else if (tmp.isDirectory()) {
	                getAllFile(tmp.getPath());
	            }
	        }
	    }
	    
	    return allFiles;
	}
	
	/**
	 * @Description 统计文件内代码行数
	 * @Author .Mark
	 * @Date 2019年2月1日
	 */
	private int countLinesOfCode(File file) {
	    int sum = 0;
	    try {
            // 1.读取文件
            if (file.isFile() && file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8"); 
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                
                // 2.文件按行存入ArrayList
                List<String> fileList = new ArrayList<>();
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    fileList.add(lineTxt);
                }
                
                // 3.统计代码量
                int startNum = 0;
                int endNum = 0;
                if (fileList != null && !fileList.isEmpty()) {
                    // 3.1.找到类代码开始行
                    int classCodeStartNum = 0;
                    for (int i = 0; i < fileList.size(); i++) {
                        String thisLine = fileList.get(i);
                        if (thisLine != null && thisLine.contains("{")) {
                            classCodeStartNum = i;
                            break;
                        }
                    }
                    
                    for (int i = classCodeStartNum + 1; i < fileList.size(); i++) {
                        // 3.2.找到注释，确定这个方法是我写的
                        String thisLine = fileList.get(i).replace(" ", "");
                        boolean myMethod = (thisLine != null) && thisLine.contains(AUTHOR_MARK) && fileList.get(i + 1).replace(" ", "").contains(DATE_2018);
                        if (myMethod) {
                            // 3.3.根据“{”确定注释下的方法开始，方法开始行作为startNum
                            for (int j = i + 2; j < fileList.size(); j++) {
                                thisLine = fileList.get(j);
                                if (thisLine != null && thisLine.contains("{")) {
                                    startNum = j;
                                    break;
                                }
                            }
                            
                            // 3.4.根据“}”确定方法结束，方法结束行作为endNum
                            int numOfOpenBrace = 1;
                            for (int j = startNum + 1; j < fileList.size(); j++) {
                                thisLine = fileList.get(j);
                                if (thisLine != null && thisLine.contains("{")) {
                                    numOfOpenBrace++;
                                }
                                if (thisLine != null && thisLine.contains("}")) {
                                    numOfOpenBrace--;
                                }
                                if (numOfOpenBrace == 0) {
                                    endNum = j;
                                    i = j + 1;
                                    sum += (endNum - startNum + 1);
                                    break;
                                }
                            }
                        }
                    }
                }
                
                fileInputStream.close();
                inputStreamReader.close();
                bufferedReader.close();
            } else {
                System.err.println("file not available");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
	    return sum;
	}
}