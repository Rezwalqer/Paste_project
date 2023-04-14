package pro.sky.myawesomepastebin.dto;

import lombok.Data;
import pro.sky.myawesomepastebin.model.Paste;

@Data
public class PasteCheck {
    private String link;
    private String body;
    private String title;



    public static PasteCheck fromPaste(Paste paste) {
        PasteCheck dto = new PasteCheck();
        dto.setLink(paste.getLink());
        dto.setBody(paste.getBody());
        dto.setTitle(paste.getTitle());
        return dto;
    }
}
