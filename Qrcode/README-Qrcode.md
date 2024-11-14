# QR code

[国标](https://openstd.samr.gov.cn/bzgk/gb/newGbInfo?hcno=452EB59E906021D3BEA11419CA96FA9A)


> 简单示例
>
> [相关资源下载](https://github.com/heyqing/practice)
>

## zxing 实现

**生成**

```java
	/**
     * 生成二维码
     *
     * @param width
     * @param height
     * @param name
     * @param format
     * @param content
     */
    public static String generateQRCodeByZxing(int width, int height, String name, String format, String content) {
        /**
         * 定义二维码参数
         */
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);

        //生成二维码
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height);
            String filename = name + "." + format;
            Path filepath = Paths.get("QRCode", "src", "com", "heyqing", "generate", filename);
            MatrixToImageWriter.writeToPath(bitMatrix, format, filepath);
            MatrixToImageWriter.writeToPath(bitMatrix, format, filepath);
            System.out.println("二维码生成完成！！！ \n请前往【" + filepath.toString() + "】查看");
            return filepath.toString();
        } catch (WriterException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
```

**解析**

```java
 	/**
     * 解析二维码
     *
     * @param filepath
     * @return
     */
    public static Result parserQRCodeByZxing(String filepath) {
        MultiFormatReader formatReader = new MultiFormatReader();
        File file = new File(filepath);
        try {
            BufferedImage image = ImageIO.read(file);
            BinaryBitmap binaryBitmap = new BinaryBitmap(
                    new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            /**
             * 定义二维码参数
             */
            Map<DecodeHintType, Object> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, "utf-8");

            Result result = formatReader.decode(binaryBitmap, hints);
            System.out.println("解析结果：" + result.toString());
            System.out.println("二维码格式：" + result.getBarcodeFormat());
            System.out.println("二维码文本内容：" + result.getText());
            image.flush();
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
```

**测试**

```java
	/**
     * zxing
     */
    private static void zxingTest() {
        String filepath = QRCodeByZxing.generateQRCodeByZxing(300, 300,
                "github", "png", "https://github.com/heyqing");
        Result result = QRCodeByZxing.parserQRCodeByZxing(filepath);
        System.out.println(result.toString());
    }
```

![GitHub](https://i-blog.csdnimg.cn/direct/65b1a428807a45f6986698a3d11d90c6.png)


## QR code 实现

**生成**

```java
	/**
     * 生成二维码
     *
     * @param name
     * @param format
     * @param content
     * @return
     */
    public static String generateQRCodeByQRCode(String name, String format, String content) {
        Qrcode qrcode = new Qrcode();
        /**
         * 纠错等级，建议 M
         */
        qrcode.setQrcodeErrorCorrect('M');
        /**
         * 字符类型，A：代表字母，N：代表数字，B:代表其他字符
         */
        qrcode.setQrcodeEncodeMode('B');
        /**
         * 版本，建议 7
         */
        int version = 7;
        qrcode.setQrcodeVersion(version);

        int width = 67 + 12 * (version - 1);
        int height = 67 + 12 * (version - 1);
        /**
         * 设置绘图基础面板
         */
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setBackground(Color.WHITE);
        graphics.setColor(Color.BLACK);
        graphics.clearRect(0, 0, width, height);
        /**
         * 偏移量
         */
        int pixoff = 2;
        byte[] contentBytes = content.getBytes();
        /**
         * 字节填充绘图
         */
        if (contentBytes.length > 0 && contentBytes.length < 120) {
            boolean[][] s = qrcode.calQrcode(contentBytes);
            for (int i = 0; i < s.length; i++) {
                for (int j = 0; j < s.length; j++) {
                    if (s[j][i]) {
                        graphics.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                    }
                }
            }
        }
        graphics.dispose();
        image.flush();
        String filename = name + "." + format;
        String path = Paths.get("QRCode", "src", "com", "heyqing", "generate", filename).toString();
        File filepath = new File(path);
        try {
            ImageIO.write(image, format, filepath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("二维码生成完成！！！ \n请前往【" + filepath.toString() + "】查看");
        return filepath.toString();
    }

```



**解析**

```java
	/**
     * 解析二维码
     *
     * @param filepath
     * @return
     */
    public static String parserQRCodeByQRCode(String filepath) {
        File file = new File(filepath);
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            QRCodeDecoder qrCodeDecoder = new QRCodeDecoder();
            String result = new String(qrCodeDecoder.decode(new MyQRCodeImage(bufferedImage)), "gb2312");
            System.out.println("解析结果：" + result);
            bufferedImage.flush();
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
```



**测试**

```java
/**
     * qrcode
     */
    private static void qrcodeTest() {
        String filepath = QRCodeByQRCode.generateQRCodeByQRCode("gitee", "png", "https://gitee.com/heyqing");
        String res = QRCodeByQRCode.parserQRCodeByQRCode(filepath);
        System.out.println(res);
    }
```

![Gitee](https://i-blog.csdnimg.cn/direct/89da0a2adda6468bbf7917ca614d4868.png)


## jQuery 实现

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>jQuery-generate-qrcode</title>
    <script type="text/javascript" src="<%=request.getContextPath() %> /js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %> /js/jquery.qrcode.min.js"></script>
</head>
<body>
<h1>生成的二维码如下：</h1><br>
<div id="qrcode"></div>
<script type="text/javascript">
    jQuery('#qrcode').qrcode("https://blog.csdn.net/heyiqingsong")
</script>
</body>
</html>
```

![csdn](https://i-blog.csdnimg.cn/direct/43ae62ae66974ec9812ff541b018e306.png)
## 更多
二维的生成还有众多的样式可选择，敬请期待更多！！！