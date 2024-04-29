package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UnitChooseChallenge {

    @Test
    public void testConstructorAndGetChallenge() {
        int difficulty = 3;
        Challenge challenge = new Challenge(difficulty);
        String selectedChallenge = challenge.getChallenge();
        Assertions.assertNotNull(selectedChallenge);
        Assertions.assertTrue(selectedChallenge.length() > 0);
    }

    @Test
    public void testSetChallenge() {
        int difficulty = 2;
        Challenge challenge = new Challenge(difficulty);
        String selectedChallenge = challenge.setChallenge();
        Assertions.assertNotNull(selectedChallenge);
        Assertions.assertTrue(selectedChallenge.length() > 0);
    }

    @Test
    public void testChooseChallenge() {
        int difficulty = 4;
        String selectedChallenge = RaceToTheRaft.chooseChallenge(difficulty);
        Assertions.assertNotNull(selectedChallenge);
        Assertions.assertTrue(selectedChallenge.length() > 0);
    }

    @Test
    public void testChooseChallengeInvalidDifficulty() {
        int difficulty = -1;
        Assertions.assertThrows(AssertionError.class, () -> {
            RaceToTheRaft.chooseChallenge(difficulty);
        });
    }
}
