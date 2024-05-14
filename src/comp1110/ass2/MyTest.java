package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;

public class MyTest {

        private final String[][] fireExpected = new String[][]{
                new String[]{
                        """
gffrrffff
gGfrRfbBb
gggrrfbbY
ppgyrybgg
ygbrrybyy
pgbyrybgg
gbrgpprpb
rypygbgby
rgbpyrpyr
grgpybygr
gyprpgbyb
brbbrbgyr
gfpgpypbg
fffrbgpry
ffppyrygb
fffrgggyp
fffpbyrow
fffgybbww
""",
                        "AabcdefhijklmnopqrstuvwxyBabcdefghijklmnopqrstuvwxyCabcdefghijklmnopqrstuvwxyDabcdefghijklmopqtwxy","AgBCDnruv","","bcdefghijklmnopqrstuvwxyzABCDE"},
                new String[]{
                        """
gffrrffff
gGfrrfbBb
gggrrfbbY
ppgyrybgg
ygbrrybyy
pgbyRybgg
gbrgrrbpb
rypyrbbby
rgbpbbbyr
grgpybygr
fyprpgbyb
fffbrbgyr
ffpgpypbg
fffrbgpry
ffppyrygb
fffrgggyp
fffpbyrow
fffgybbww
""",
                        "AabcdefhijklmnopqrstuvwxyBabcdefghijklmnopqrstuvwxyCabcdefghijklmnopqrstuvwxyDabcdefghijklmopqtwxy","ABCDnrv","R0504","bcdefgijklmnopqrstuvwxyzABCDE"},
                new String[]{
                        """
fffgffffyffffppgff
fffgGffYyffffPpgGf
fffgggyyyffffpRggg
fffbggygrfffgpypbg
fffpbgygyfffrbgpry
fffyrgggpfffpyrygb
fffgbbrpbfffrggbpg
fffpggbygfffpbyryb
fffpypgrrfffgybrgy
ffffffbgyyrbpbygpb
fffbBbpbgggygrbpyr
ffffbbyrppygbybgrg
fffffrggrpbyygpybg
fffpffypbrorpbrgpb
fffgfprybgwggrbpyy
""",
                        "AabcdefghijklmnopqrstuvwxyBabdefhijklmopqrstuvwxyCabcdefghijklmnopqrstuvxyDacdefghijklmnoprstuvwxy","ABcgnCDbq","","abcdefghijkmnopqrstuvwxyzABCDE"},
                new String[]{
                        """
fffgffffyffffppgff
ffffgffyyffffPpgGf
ffffffyyyffffpRggg
fffbgfygrfffgpppgg
fffpbgYgyfffrbgpgy
fffyrGggpfffpygpgb
fffgbbrpbfffrggbpg
fffpggbygfffpbyryb
fffpypgrrfffgybrgy
ffffffbgyyrbpbygpb
fffbBbpbgggygrbpyr
ffffbbyrppygbybgrg
fffffrggrpbyygpybg
fffpffypbrorpbrgpb
fffgfprybgwggrbpyy
""",
                        "AabcdefghijklmnopqrstuvwxyBabdefhijklmopqrstuvwxyCabcdefghijklmnopqrstuvxyDacdefghijklmnoprstuvwxy","ABgCDq","G0505Y0406","abcdefghijkmnopqrstuvxyzABCDE"},

        };



}
