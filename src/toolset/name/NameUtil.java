package toolset.name;

import java.util.Random;

/**
 * @Description 姓名工具类
 * @Author .Mark
 * @Date 2019年3月4日
 */
public class NameUtil {
    /**
     * @Description 随机获取常用姓氏
     * @Author .Mark
     * @Date 2018年5月16日
     */
    public static String getName() {  
        Random random=new Random(System.currentTimeMillis());  
        /* 598 百家姓 */  
        String[] Surname= {"赵","钱","孙","李","周","吴","郑","王","冯","陈","褚","卫","蒋","沈","韩","杨","朱","秦","尤","许",  
                  "何","吕","施","张","孔","曹","严","华","金","魏","陶","姜","戚","谢","邹","喻","柏","水","窦","章","云","苏","潘","葛","奚","范","彭","郎",  
                  "鲁","韦","马","苗","花","方","任","袁","柳","鲍","史","唐","费","薛","雷","贺","倪","汤","滕","殷",  
                  "罗","毕","郝","安","常","于","齐","康","伍","余","孟","黄",  
                  "穆","萧","尹","姚","邵","司马","上官","欧阳","夏侯","诸葛","东方",  
                  "公孙","令狐","钟离","慕容","司徒","司空",  
                  "呼延","端木","西门","南宫"};  
          
        int index=random.nextInt(Surname.length-1);       
        String name = Surname[index]; //获得一个随机的姓氏  
          
        /* 从常用字中选取一个或两个字作为名 */  
        if(random.nextBoolean()){  
            name+=getChinese()+getChinese();  
        }else {  
            name+=getChinese();  
        }  
        return name;  
    }  
          
    /**
     * @Description 随机获取常用汉字
     * @Author .Mark
     * @Date 2018年5月16日
     */
    private static String getChinese() {  
        StringBuilder dailyHanzi = new StringBuilder();
        dailyHanzi.append("的一了是我不在人们有来他这上着个地到大里说就去子得也和那要下看天时过出小么起你都把好还多没为又可家学只以主会样年想生同老中十从自面前头道它细")
                  .append("后然走很像见两用她国动进成回什边作对开而己些现山民候经发工向事命给长水几义三声于高手知理眼志点心战二问但身方实吃做叫当住听革打呢真全才四已所")
                  .append("敌之最光产情路分总条白话东席次亲如被花口放儿常气五第使写军吧文运再果怎定许快明行因别飞外树物活部门无往船望新带队先力完却站代员机更九您每风级")
                  .append("跟笑啊孩万少直意夜比阶连车重便斗马哪化太指变社似士者干石满日决百原拿群究各六本思解立河村八难早论吗根共让相研今其书坐接应关信觉步反处记将千找")
                  .append("争领或师结块跑谁草越字加脚紧爱等习阵怕月青半火法题建赶位唱海七女任件感准张团屋离色脸片科倒睛利世刚且由送切星导晚表够整认响雪流未场该并底深刻")
                  .append("平伟忙提确近亮轻讲农古黑告界拉名呀土清阳照办史改历转画造嘴此治北必服雨穿内识验传业菜爬睡兴形量咱观苦体众通冲合破友度术饭公旁房极南枪读沙岁线")
                  .append("野坚空收算至政城劳落钱特围弟胜教热展包歌类渐强数乡呼性音答哥际旧神座章帮啦受系令跳非何牛取入岸敢掉忽种装顶急林停息句区衣般报叶压慢叔背");
       Random random = new Random();
       return "" + dailyHanzi.charAt(random.nextInt(dailyHanzi.length()));
    }  
}
