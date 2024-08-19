package com.rtm.application.util;

import java.io.*;

public class GenerateJavaFile {

    public static void main(String[] args) {
//        generateEntityClassFile();
        generateEntityInterfaceFile();
    }

    public static void generateEntityClassFile() {
        // 获取类名（通常可以从args中获取，或通过用户输入）
        // 定义文件名和目录
        String reqDirectoryPath = "D:\\download\\chrome\\app-transition\\src\\main\\java\\com\\rtm\\application\\protocol\\message\\entity\\api\\req\\"; // 文件生成路径

        String resDirectoryPath = "D:\\download\\chrome\\app-transition\\src\\main\\java\\com\\rtm\\application\\protocol\\message\\entity\\api\\res\\"; // 文件生成路径

        File file = new File("E:\\aa.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String className;

            while ((className = br.readLine()) != null) {
                className = className.trim();
                if (!className.isEmpty()) {
                    // 为每个类名生成实体类文件
                    String reqPackageName = "package com.rtm.application.protocol.message.entity.api.req;\n\nimport com.rtm.application.protocol.message.entity.RequestBody; \n\n";
                    String resPackageName = "package com.rtm.application.protocol.message.entity.api.res;\n\nimport com.rtm.application.protocol.message.entity.ResponseBody; \n\n";
                    String resExtendName = " extends RequestBody";
                    String repExtendName = " extends ResponseBody";
                    generateEntityClassFile(reqDirectoryPath,className+"RequestBody",reqPackageName,resExtendName);
                    generateEntityClassFile(resDirectoryPath,className+"ResponseBody",resPackageName,repExtendName);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据给定的类名生成Java实体类文件
     *
     * @param className 类名
     */
    public static void generateEntityClassFile(String directoryPath,String className,String packagePath,String extendsName) {
        String fileName = className + ".java";
        String filePath = directoryPath + "/" + fileName;

        // 创建目录
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs(); // 创建多级目录
        }

        // 创建文件
        File file = new File(filePath);
        try (FileWriter writer = new FileWriter(file)) {
            // 写入包声明
            writer.write(packagePath);

            // 写入类的注释
            writer.write("/**\n");
            writer.write(" * " + className + " 解析消息体.\n");
            writer.write(" */\n");

            // 写入类声明
            writer.write("public class " + className + extendsName +"  {\n");

            // 写入类结束
            writer.write("}\n");

            System.out.println("Java 实体类文件已生成: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateEntityInterfaceFile() {
        // 获取类名（通常可以从args中获取，或通过用户输入）
        // 定义文件名和目录
        String reqDirectoryPath = "D:\\download\\chrome\\app-transition\\src\\main\\java\\com\\rtm\\application\\protocol\\api\\parser\\req\\"; // 文件生成路径

        String resDirectoryPath = "D:\\download\\chrome\\app-transition\\src\\main\\java\\com\\rtm\\application\\protocol\\api\\parser\\res\\"; // 文件生成路径

        File file = new File("E:\\aa.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String className;

            while ((className = br.readLine()) != null) {
                className = className.trim();
                if (!className.isEmpty()) {
                    // 为每个类名生成实体类文件
                    StringBuilder req = new StringBuilder();
                    req.append("package com.rtm.application.protocol.api.parser.req;");
                    req.append("\n\n");
                    req.append("import com.rtm.application.protocol.KafkaProtocolParser;");
                    req.append("\n");
//                    req.append("import com.rtm.application.protocol.KafkaParserFactory;");
//                    req.append("\n");
                    req.append("import com.rtm.application.protocol.message.entity.api.req.");
                    req.append(className+"RequestBody;");
                    req.append("\n");
                    req.append("import com.rtm.application.protocol.message.exception.ProtocolParseException;");
                    req.append("\n");
                    req.append("import java.nio.ByteBuffer;");
                    req.append("\n\n");


                    String reqClzName = className+"RequestBody";
                    String reqExtendName = " implements KafkaProtocolParser<"+reqClzName+">";

                    String resClzName = className+"ResponseBody";
                    String resExtendName = " implements KafkaProtocolParser<"+resClzName+">";

                    StringBuilder res = new StringBuilder();
                    res.append("package com.rtm.application.protocol.api.parser.res;");
                    res.append("\n\n");
                    res.append("import com.rtm.application.protocol.KafkaProtocolParser;");
                    res.append("\n");
//                    res.append("import com.rtm.application.protocol.KafkaParserFactory;");
//                    res.append("\n");
                    res.append("import com.rtm.application.protocol.message.entity.api.res.");
                    res.append(resClzName+";");
                    res.append("\n");
                    res.append("import com.rtm.application.protocol.message.exception.ProtocolParseException;");
                    res.append("\n");
                    res.append("import java.nio.ByteBuffer;");
                    res.append("\n\n");

                    generateEntityInterfaceFile(reqDirectoryPath,className+"RequestParser",req.toString(),reqExtendName,reqClzName,true);
                    generateEntityInterfaceFile(resDirectoryPath,className+"ResponseParser",res.toString(),resExtendName,resClzName,false);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据给定的类名生成Java实体类文件
     *
     * @param className 类名
     */
    public static void generateEntityInterfaceFile(String directoryPath,String className,String packagePath,String extendsName,String clsName,boolean request) {
        String fileName = className + ".java";
        String filePath = directoryPath + "/" + fileName;

        // 创建目录
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs(); // 创建多级目录
        }

        // 创建文件
        File file = new File(filePath);
        try (FileWriter writer = new FileWriter(file)) {
            // 写入包声明
            writer.write(packagePath);

            // 写入类的注释
            writer.write("/**");
            writer.write("\n");
            writer.write(" * @author rtm");
            writer.write("\n");
            writer.write(" * @date 2024/08/15");
            writer.write("\n");
            writer.write(" * " + className + " 协议 payload 解析器.\n");
            writer.write(" */\n");

            // 写入类声明
            writer.write("public class " + className + extendsName +" {\n");
            writer.write("\n\n");

//            writer.write("    static {\n");
//            writer.write("        KafkaParserFactory.registerParser(new "+className+"());;");
//            writer.write("\n");
//            writer.write("    }");
//            writer.write("\n\n");

            writer.write("    @Override");
            writer.write("\n");
            writer.write("    public "+clsName+" parsePacket(ByteBuffer payload) throws ProtocolParseException {\n");
            writer.write("        return null;");
            writer.write("\n");
            writer.write("    }");
            writer.write("\n\n");


            writer.write("    @Override");
            writer.write("\n");
            writer.write("    public short getVersion() {\n");
            writer.write("        return 0;");
            writer.write("\n");
            writer.write("    }");
            writer.write("\n\n");

            writer.write("    @Override");
            writer.write("\n");
            writer.write("    public short getApiKey() {\n");
            writer.write("        return 0;\n");
            writer.write("    }");
            writer.write("\n\n");

            writer.write("    @Override");
            writer.write("\n");
            writer.write("    public boolean isRequestParser() {\n");
            writer.write("        return ");
            writer.write(""+request+";");
            writer.write("\n");
            writer.write("    }");
            writer.write("\n\n");


            // 写入类结束
            writer.write("}\n");

            System.out.println("Java 接口文件已生成: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
