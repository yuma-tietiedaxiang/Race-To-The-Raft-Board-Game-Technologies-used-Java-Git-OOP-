package comp1110.ass2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CatTest {
    @Test
    public void testCatCardPlacement() {
        // 创建一个示例的岛屿挑战字符串
        String challengeString = "LNSNLASAF000300060012001506030903C000093030341203R11215";

        // 从挑战字符串中提取初始猫卡位置字符串
        String catSubstring = extractCatSubstring(challengeString);

        // 断言初始猫卡位置字符串的格式是否正确
        assertTrue(isCatSubstringValid(catSubstring));

        // 解析初始猫卡位置字符串,并创建对应的 CatCard 对象
        CatCard[] catCards = parseCatSubstring(catSubstring);

        // 断言解析后的猫卡数量是否正确
        assertEquals(3, catCards.length);

        // 断言每张猫卡的位置是否正确
        assertEquals(0, catCards[0].getId());
        assertEquals(0, catCards[0].getRow());
        assertEquals(9, catCards[0].getColumn());

        assertEquals(3, catCards[1].getId());
        assertEquals(3, catCards[1].getRow());
        assertEquals(3, catCards[1].getColumn());

        assertEquals(4, catCards[2].getId());
        assertEquals(12, catCards[2].getRow());
        assertEquals(3, catCards[2].getColumn());
    }

    // 从挑战字符串中提取初始猫卡位置字符串
    private String extractCatSubstring(String challengeString) {
        int catIndex = challengeString.indexOf('C');
        int rowIndex = challengeString.indexOf('R');
        return challengeString.substring(catIndex + 1, rowIndex);
    }

    // 验证初始猫卡位置字符串的格式是否正确
    private boolean isCatSubstringValid(String catSubstring) {
        // 检查字符串长度是否是5的倍数
        if (catSubstring.length() % 5 != 0) {
            return false;
        }

        // 检查每个猫卡的格式是否正确
        for (int i = 0; i < catSubstring.length(); i += 5) {
            String catCardString = catSubstring.substring(i, i + 5);
            if (!catCardString.matches("\\d{5}")) {
                return false;
            }
        }

        return true;
    }

    // 解析初始猫卡位置字符串,并创建对应的 CatCard 对象
    private CatCard[] parseCatSubstring(String catSubstring) {
        int numCatCards = catSubstring.length() / 5;
        CatCard[] catCards = new CatCard[numCatCards];

        for (int i = 0; i < numCatCards; i++) {
            String catCardString = catSubstring.substring(i * 5, (i + 1) * 5);
            int id = Character.getNumericValue(catCardString.charAt(0));
            int row = Integer.parseInt(catCardString.substring(1, 3));
            int column = Integer.parseInt(catCardString.substring(3, 5));
            catCards[i] = new CatCard(id, row, column);
        }

        return catCards;
    }
}

// 表示猫卡的类
class CatCard {
    private int id;
    private int row;
    private int column;

    public CatCard(int id, int row, int column) {
        this.id = id;
        this.row = row;
        this.column = column;
    }

    public int getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}