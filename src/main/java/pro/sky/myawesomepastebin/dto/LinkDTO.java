package pro.sky.myawesomepastebin.dto;

import lombok.Data;
import pro.sky.myawesomepastebin.model.Paste;
@Data
public class LinkDTO {
    private String link;
    public static LinkDTO fromPaste(Paste paste) {
        LinkDTO dto = new LinkDTO();
        dto.setLink(paste.getLink());
        return dto;
    }

    public PasteCreateDTO toPaste() {
        PasteCreateDTO dto = new PasteCreateDTO();
        dto.setLink(this.getLink());
        return dto;
    }
}
