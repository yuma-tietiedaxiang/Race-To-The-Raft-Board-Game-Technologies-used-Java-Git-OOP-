package comp1110.ass2;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private final DeckType deckType;
    //private ArrayList<Card> cards;
    private String deckInfo;



    /*private char deckIDA = 'A';//private....
    private char deckIDB = 'B';//private....
    private char deckIDC = 'C';//private....
    private char deckIDD = 'D';//private....



    Pathway a1 = new Pathway("abgbbgybby",1);
    Pathway a2 = new Pathway("bgbbbrrbrr",2);
    Pathway a3 = new Pathway("crrrrgrggy",3);
    Pathway a4 = new Pathway("dggbygbygb",4);
    Pathway a5 = new Pathway("errgrrbgbb",5);
    Pathway a6 = new Pathway("fryrryrryr",6);
    Pathway a7 = new Pathway("grrbrbbbbb",7);
    Pathway a8 = new Pathway("hbbrbyrbyb",8);
    Pathway a9 = new Pathway("irbyrbyrry",9);
    Pathway a10 = new Pathway("jbbbbrrbrr",10);
    Pathway a11 = new Pathway("krrygrygrr",11);
    Pathway a12 = new Pathway("lrrygryrgg",12);
    Pathway a13 = new Pathway("mbbybbrgrr",13);
    Pathway a14 = new Pathway("nrrrrbyrby",14);
    Pathway a15 = new Pathway("obbbbgbygy",15);
    Pathway a16 = new Pathway("prrryrgyrg",16);
    Pathway a17 = new Pathway("qbbbybgybb",17);
    Pathway a18 = new Pathway("rrybrybbyb",18);
    Pathway a19 = new Pathway("sgbbrrbrrr",19);
    Pathway a20 = new Pathway("trgbrgbrgb",20);
    Pathway a21 = new Pathway("ugyrgyrggr",21);
    Pathway a22 = new Pathway("vybrybrybr",22);
    Pathway a23 = new Pathway("wggrgrrrrr",23);
    Pathway a24 = new Pathway("xbbygbygbb",24);
    Pathway a25 = new Pathway("ybgbbgbbgb",25);


    Pathway b1 = new Pathway("agppgpyggy",26);
    Pathway b2 = new Pathway("brrrgbggbg",27);
    Pathway b3 = new Pathway("crbyrbyrby",28);
    Pathway b4 = new Pathway("dgggygrygr",29);
    Pathway b5 = new Pathway("eyggbggbbr",30);
    Pathway b6 = new Pathway("fbbybpybpp",31);
    Pathway b7 = new Pathway("gggggpggpr",32);
    Pathway b8 = new Pathway("hggggggbbb",33);
    Pathway b9 = new Pathway("ibbypbypbb",34);
    Pathway b10 = new Pathway("jbbbbrbrry",35);
    Pathway b11 = new Pathway("kgbgbgbgbg",36);
    Pathway b12 = new Pathway("lbgbggbbbg",37);
    Pathway b13 = new Pathway("mrrgrggggg",38);
    Pathway b14 = new Pathway("nggppppggg",39);
    Pathway b15 = new Pathway("oggbggbpgg",40);
    Pathway b16 = new Pathway("pbbbbbbggg",41);
    Pathway b17 = new Pathway("qbbbybpybp",42);
    Pathway b18 = new Pathway("rbbbbgybgy",43);
    Pathway b19 = new Pathway("sbyrbrrbbr",44);
    Pathway b20 = new Pathway("tgrrgrrggg",45);
    Pathway b21 = new Pathway("ubrbbrbbrb",46);
    Pathway b22 = new Pathway("vpyrpyrpyr",47);
    Pathway b23 = new Pathway("wggygrygry",48);
    Pathway b24 = new Pathway("xygrygrygr",49);
    Pathway b25 = new Pathway("ybpgbpgbpg",50);


    Pathway c1 = new Pathway("ayyybyrbyr",51);
    Pathway c2 = new Pathway("bgrggrggrg",52);
    Pathway c3 = new Pathway("cbyybyrryr",53);
    Pathway c4 = new Pathway("dyyyyryprg",54);
    Pathway c5 = new Pathway("epybpybpyb",55);
    Pathway c6 = new Pathway("fyybgybgyy",56);
    Pathway c7 = new Pathway("gppgpggggg",57);
    Pathway c8 = new Pathway("hggggrrgrr",58);
    Pathway c9 = new Pathway("iyygyggppp",59);
    Pathway c10 = new Pathway("jgggygpygp",60);
    Pathway c11 = new Pathway("kypyypyygy",61);
    Pathway c12 = new Pathway("lrrggggygg",62);
    Pathway c13 = new Pathway("mggbgpbgpb",63);
    Pathway c14 = new Pathway("nrrrgpggpg",64);
    Pathway c15 = new Pathway("oyybyrbyrr",65);
    Pathway c16 = new Pathway("pggyygyygg",66);
    Pathway c17 = new Pathway("qybpyppyyp",67);
    Pathway c18 = new Pathway("rbgpbgpbgp",68);
    Pathway c19 = new Pathway("syyyygyygy",69);
    Pathway c20 = new Pathway("trggyggyyp",70);
    Pathway c21 = new Pathway("uppyyyyrry",71);
    Pathway c22 = new Pathway("vgrggryggg",72);
    Pathway c23 = new Pathway("wggggyggyg",73);
    Pathway c24 = new Pathway("xyryyryyry",74);
    Pathway c25 = new Pathway("yggggggyyy",75);


    Pathway d1 = new Pathway("appbgpbgpp",76);
    Pathway d2 = new Pathway("bppppgpygb",77);
    Pathway d3 = new Pathway("cpbrprrppr",78);
    Pathway d4 = new Pathway("dyyryypbpp",79);
    Pathway d5 = new Pathway("egggyrpyrp",80);
    Pathway d6 = new Pathway("fyypygpygp",81);
    Pathway d7 = new Pathway("grryryyyyy",82);
    Pathway d8 = new Pathway("hyypgypgyy",83);
    Pathway d9 = new Pathway("iggybgybgy",84);
    Pathway d10 = new Pathway("jygpygpygp",85);
    Pathway d11 = new Pathway("kyggygbyyb",86);
    Pathway d12 = new Pathway("lyyybybbyp",87);
    Pathway d13 = new Pathway("mppbpgbpgg",88);
    Pathway d14 = new Pathway("nggpgppppp",89);
    Pathway d15 = new Pathway("ogrpgrpggp",90);
    Pathway d16 = new Pathway("pyyyybybbb",91);
    Pathway d17 = new Pathway("qgyyppyppp",92);
    Pathway d18 = new Pathway("rpbbpbbgbb",93);
    Pathway d19 = new Pathway("sbyrbyrbyr",94);
    Pathway d20 = new Pathway("tpgypgypgy",95);
    Pathway d21 = new Pathway("uppppybpyb",96);
    Pathway d22 = new Pathway("vyyyypbybb",97);
    Pathway d23 = new Pathway("wrpbrpbrpb",98);
    Pathway d24 = new Pathway("xpppbyybyy",99);
    Pathway d25 = new Pathway("ypprppyryy",100);*/
    public Deck(char type, String deckInfo){
        this.deckType = DeckType.fromChar(type);
        this.deckInfo=deckInfo;
    }
    public String getDeckInfo(){
        return deckInfo;
    }
    public boolean hasNoCard(){// see if this deck is empty
        if (deckInfo.isEmpty()) {
            return true;}
        return false;
    }

    public char drawCards(){
        Random random = new Random();
        int index = random.nextInt(deckInfo.toCharArray().length);
        char randomChar = deckInfo.charAt(index);
        deckInfo = deckInfo.replace(String.valueOf(randomChar), "");
        return randomChar;
    }

}
