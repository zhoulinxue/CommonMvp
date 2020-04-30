package org.zhx.common.mvp.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * pakage :com.gaea.kiki.utils
 * auther :zx
 * creatTime: 2019/7/3
 * description :
 */
public class GenerateValueFiles {
    private int baseW;
    private int baseH;

    private String dirStr = "./mvplibrary/src/main/res";

    private final static String WTemplate = "<dimen name=\"x{0}\">{1}px</dimen>\n";
    private final static String HTemplate = "<dimen name=\"y{0}\">{1}px</dimen>\n";
    private final static String SPTemplate = "<dimen name=\"sp_{0}\">{1}sp</dimen>\n";

    /**
     * {0}-HEIGHT
     */
    private final static String VALUE_TEMPLATE = "values-{0}x{1}";

    private static final String SUPPORT_DIMESION = "0,0;320,480;480,800;480,854;540,960;600,1024;720,1184;720,1196;720,1280;768,1024;800,1280;1080,1812;1080,1920;1440,2560;";

    private String supportStr = SUPPORT_DIMESION;

    public GenerateValueFiles(int baseX, int baseY, String supportStr) {
        System.out.println(getClass().getClassLoader().getResource("./app/src/main/res"));
        this.baseW = baseX;
        this.baseH = baseY;

        if (!this.supportStr.contains(baseX + "," + baseY)) {
            this.supportStr += baseX + "," + baseY + ";";
        }

        this.supportStr += validateInput(supportStr);

        System.out.println(supportStr);

        File dir = new File(dirStr);
        if (!dir.exists()) {
            dir.mkdir();

        }
        System.out.println(dir.getAbsoluteFile());

    }

    /**
     * @param supportStr w,h_...w,h;
     * @return
     */
    private String validateInput(String supportStr) {
        StringBuffer sb = new StringBuffer();
        String[] vals = supportStr.split("_");
        int w = -1;
        int h = -1;
        String[] wh;
        for (String val : vals) {
            try {
                if (val == null || val.trim().length() == 0)
                    continue;

                wh = val.split(",");
                w = Integer.parseInt(wh[0]);
                h = Integer.parseInt(wh[1]);
            } catch (Exception e) {
                System.out.println("skip invalidate params : w,h = " + val);
                continue;
            }
            sb.append(w + "," + h + ";");
        }

        return sb.toString();
    }

    public void generate() {
        String[] vals = supportStr.split(";");
        for (String val : vals) {
            String[] wh = val.split(",");
            int w=Integer.parseInt(wh[0]);
            int h=Integer.parseInt(wh[1]);
            if(w!=0)
                generateXmlFile(w, h);
            else {
                generateXmlFile();
            }
        }

    }

    private void generateXmlFile() {
        StringBuffer sbForWidth = new StringBuffer();
        sbForWidth.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sbForWidth.append("<resources>");
        for (int i = 1; i < baseW; i++) {
            sbForWidth.append(WTemplate.replace("{0}", i + "").replace("{1}",
                    change(i) + ""));
        }
        sbForWidth.append(WTemplate.replace("{0}", baseW + "").replace("{1}",
                baseW + ""));

        for (int i = 1; i < baseH; i++) {
            sbForWidth.append(HTemplate.replace("{0}", i + "").replace("{1}",
                    change( i) + ""));
        }
        sbForWidth.append(HTemplate.replace("{0}", baseH + "").replace("{1}",
                baseH + ""));

        for (int i = 1; i < 50; i++) {
            sbForWidth.append(SPTemplate.replace("{0}", i + "").replace("{1}",
                    i + ""));
        }
        sbForWidth.append("</resources>");
        File fileDir = new File(dirStr + File.separator
                + VALUE_TEMPLATE.replace("-{0}x{1}",  ""));
        fileDir.mkdir();

        File layxFile = new File(fileDir.getAbsolutePath(), "dimen.xml");
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(layxFile));
            pw.print(sbForWidth.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void generateXmlFile(int w, int h) {

        StringBuffer sbForWidth = new StringBuffer();
        sbForWidth.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sbForWidth.append("<resources>");
        float cellw = w * 1.0f / baseW;

        System.out.println("width : " + w + "," + baseW + "," + cellw);
        for (int i = 1; i < baseW; i++) {
            sbForWidth.append(WTemplate.replace("{0}", i + "").replace("{1}",
                    change(cellw * i) + ""));
        }
        sbForWidth.append(WTemplate.replace("{0}", baseW + "").replace("{1}",
                w + ""));
        float cellh = h * 1.0f / baseH;

        System.out.println("height : " + h + "," + baseH + "," + cellh);
        for (int i = 1; i < baseH; i++) {
            sbForWidth.append(HTemplate.replace("{0}", i + "").replace("{1}",
                    change(cellh * i) + ""));
        }
        sbForWidth.append(HTemplate.replace("{0}", baseH + "").replace("{1}",
                h + ""));

        for (int i = 1; i < 50; i++) {
            sbForWidth.append(SPTemplate.replace("{0}", i + "").replace("{1}",
                    i + ""));
        }
        sbForWidth.append("</resources>");

        File fileDir = new File(dirStr + File.separator
                + VALUE_TEMPLATE.replace("{0}", h + "")//
                .replace("{1}", w + ""));
        fileDir.mkdir();

        File layxFile = new File(fileDir.getAbsolutePath(), "dimen.xml");
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(layxFile));
            pw.print(sbForWidth.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static float change(float a) {
        int temp = (int) (a * 100);
        return temp / 100f;
    }

    public static void main(String[] args) {
        int baseW = 750;
        int baseH = 1334;
        String addition = "";
        try {
            if (args.length >= 3) {
                baseW = Integer.parseInt(args[0]);
                baseH = Integer.parseInt(args[1]);
                addition = args[2];
            } else if (args.length >= 2) {
                baseW = Integer.parseInt(args[0]);
                baseH = Integer.parseInt(args[1]);
            } else if (args.length >= 1) {
                addition = args[0];
            }
        } catch (NumberFormatException e) {

            System.err
                    .println("right input params : java -jar xxx.jar width height w,h_w,h_..._w,h;");
            e.printStackTrace();
            System.exit(-1);
        }

        new GenerateValueFiles(baseW, baseH, addition).generate();
    }

}

