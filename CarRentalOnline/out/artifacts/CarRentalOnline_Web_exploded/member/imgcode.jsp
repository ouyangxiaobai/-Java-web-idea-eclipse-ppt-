<%@ page  pageEncoding = "gbk" contentType="image/jpeg" import = "javax.imageio.*,java.util.*,java.awt.image.*,java.awt.*" %> 
window.self.location.reload(true);

<%! 

    //在此处 获取并生成随机颜色 

    Color getRandColor(Random random, int ff, int cc) { 

       if (ff > 255) 

           ff = 255; 

       if (cc > 255) 

           cc = 255; 

       int r = ff + random.nextInt(cc - ff); 

       int g = ff + random.nextInt(cc - ff); 

       int b = ff + random.nextInt(cc - ff); 

       return new Color(r, g, b); 

    } %> 



<%

    response.setHeader("Pragma","No-cache");

    response.setHeader("Cache-Control","no-cache");

    response.setDateHeader("Expires", 0);

 

    int width=60; //定义验证码图片的长度

    int height=20; //定义验证码图片的宽度

    BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

 

    Graphics g = image.getGraphics();

 

    Random random = new Random();

 

    g.setColor(getRandColor(random,200,250));

    g.fillRect(0, 0, width, height);

 

    g.setFont(new Font("Times New Roman",Font.PLAIN,18));


    //定义字体形式
 

    g.setColor(getRandColor(random,160,200));

    for (int i=0;i<155;i++)

    {

       int i_x = random.nextInt(width);

       int i_y = random.nextInt(height);

       int i_xl = random.nextInt(12);

       int i_yl = random.nextInt(12);

       g.drawLine(i_x,i_y,i_x+i_xl,i_y+i_yl);

    }


    //用线条画背景
 

    String s_Rand="";

    for (int i=0;i<4;i++)

    {

       String rand=String.valueOf(random.nextInt(10));

       s_Rand+=rand;

       

       g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));

       g.drawString(rand,13*i+6,16);

    }


    //产生4位随机码 
 

    session.setAttribute("rand",s_Rand);


    //将验证码存入Session中
 

    g.dispose();

    

    ImageIO.write(image, "JPEG", response.getOutputStream());


    //输出验证图片
    

    out.clear();

    out = pageContext.pushBody();

    

%>


