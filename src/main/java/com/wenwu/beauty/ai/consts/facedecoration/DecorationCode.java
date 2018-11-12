package com.wenwu.beauty.ai.consts.facedecoration;

/**
 * 变妆编码
 */
public enum DecorationCode {
    AIJI("埃及妆",1),
    TUZHU("巴西土著妆",2),
    HUIGUNIANG("灰姑娘妆",3),
    EMO("恶魔妆",4),
    WUMIENIANG("武媚娘妆",5),
    XUYICAO("星光薰衣草妆",6),
    HUAQIANGU("花千骨妆",7),
    JIANGSHI("僵尸妆",8),
    AIGUO("爱国妆",9),
    XIAOHUZI("小胡子妆",10),
    MIEYANGYANG("美羊羊妆",11),
    HUOYIN("火影鸣人妆",12),
    DAOMADAN("刀马旦妆",13),
    PAOPAO("泡泡妆",14),
    YINGHUA("樱花妆",15),
    NVHUANG("女皇妆",16),
    QUANZHILONG("权志龙妆",17),
    LIAOMEI("撩妹妆",18),
    YINDIAN("印第安妆",19),
    YINDU("印度妆",20),
    MENGTU("萌兔妆",21),
    DASHENG("大圣妆",22);

    private int value;
    private String name;
    DecorationCode(String name,int value){
        name = this.name;
        value = this.value;
    }

    public int getValue() {
        return value;
    }
    public String getName(){
        return name;
    }
}
