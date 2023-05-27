import java.awt.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static boolean openWebpage( URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean openWebpage( URL url) {
        try {
            return openWebpage(url.toURI());
        } catch ( URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main( String[] args ) {
        List<String> playlist = new ArrayList<>();
        String[] songs = {
                "https://open.spotify.com/track/16Fxe5DvEXRxQwcorFyaIO?si=cb672a34cf434360",
                "https://open.spotify.com/track/6zvqq50PL7io0rprbkrYc9?si=67558e58cc15413f",
                "https://open.spotify.com/track/4nK5YrxbMGZstTLbvj6Gxw?si=dfd9e16182b14be3",
                "https://open.spotify.com/track/23TPP1eeElFfvYVznskwCY?si=3a0af66959ed424c",
                "https://open.spotify.com/track/4Z2zWAUN9YVxHkZXS7s1KE?si=030373f2ea5d43e8",
                "https://open.spotify.com/track/7bhpOnHyttMaBCg6WGCRzi?si=05fb5ddda2224cf3",
                "https://open.spotify.com/track/5vrOwoXILW22WyXkhGLciY?si=4d95979c1abe46da",
                "https://open.spotify.com/track/06Xh1KvQofFghlSt33mFjc?si=b754f986fae642aa",
                "https://open.spotify.com/track/3smlpsXoDcTvDqHZTYfJLe?si=8733f126b66c4b1e",
                "https://open.spotify.com/track/3WowsoNoWeMqTH2suWv9MW?si=627d0413424249d9",
                "https://open.spotify.com/track/65ZaStHQImXcy15M9pGFwL?si=479a6c14bd194141",
                "https://open.spotify.com/track/4zIiarkbaDt2cm6sukb1Xt?si=1274843a81d54157",
                "https://open.spotify.com/track/4Ve0Jx7MXjU4aPrFHJRZK7?si=a71b8e0de5c94c16",
                "https://open.spotify.com/track/14rNxRzrnsyqDZ8PRfV1k3?si=f8195415873c481b",
                "https://open.spotify.com/track/1e6JmZg46vKcwhtM8hXbnK?si=98e7ce78c14d4118",
                "https://open.spotify.com/track/7IoV5eGR85wUOXjninlmNu?si=abea3a5bd6c747e9",
                "https://open.spotify.com/track/0NVxFntUSEYwPn27lX2J7r?si=09885057cfdc493e",
                "https://open.spotify.com/track/4Kjo34blxmLG5HNUKEvHRM?si=02c67fbe74654e02",
                "https://open.spotify.com/track/3la1J2DCliJL4t1QmmF2JE?si=5c52ecc6c66b45d4",
                "https://open.spotify.com/track/0jeJE3TUJ5FrRuVLr7w4JQ?si=9244beb690b245ba",
                "https://open.spotify.com/track/5WssbEU2WtMHm7NH37Nfz5?si=b6b0bb391164410a",
                "https://open.spotify.com/track/6O3JxEetVDHKG7AfcZziWZ?si=c3853c4b23f34579",
                "https://open.spotify.com/track/3ssQwT97KP7BTUtWoQTm4x?si=894ed9bb86234c91",
                "https://open.spotify.com/track/2dZ6qbPiSoemJYoFWv9e0e?si=e0008e9d58014c68",
                "https://open.spotify.com/track/3aTUSOV2EvHIZSOoqvZnLW?si=0d4522fc41ae4822",
                "https://open.spotify.com/track/0hVZCUZKWsWFU6i5zlRZZr?si=6716dedfe7084a2e",
                "https://open.spotify.com/track/35arSG83D9FCN2Ts0qOuoc?si=890109283b3441d9",
                "https://open.spotify.com/track/44Z41mjp6AgW2NaXD0OIaP?si=5014f64a139442ba",
                "https://open.spotify.com/track/00uJ2qNYu1EzaHjNRa9W6b?si=14710ef311034461",
                "https://open.spotify.com/track/5m5rjz3l2kFPX4BiU7NExf?si=265863ed34ae4d91",
                "https://open.spotify.com/track/3VDOw02xemSobR1mdkn6sE?si=51a9b3fc08e647f3",
                "https://open.spotify.com/track/48yXA9OSJ02608WYnTo4ps?si=c279771a015648a0",
                "https://open.spotify.com/track/5kFHgvYq9KkYJQx9h08b2c?si=fd78c4e963eb49f8",
                "https://open.spotify.com/track/46fsbzYXr66Vp8nSK4LvVO?si=f731922ae43a469c",
                "https://open.spotify.com/track/0nz49s3TO2tTuBLLrE1vUA?si=62b80602ffea4e38",
                "https://open.spotify.com/track/0y2WxSBonDZBmF8cEgPLiW?si=bb5838f180b446f3",
                "https://open.spotify.com/track/61ZC2mWxRDxK0jYCVKhF4x?si=5019ac9df6724c39",

        };
        Collections.addAll(playlist,songs);
        Collections.shuffle(playlist);
        URL song;
        try {
           song = new URL(playlist.get(0));
        } catch ( MalformedURLException e ) {
            throw new RuntimeException(e);
        }
        openWebpage(song);

        System.exit(0);
    }
}