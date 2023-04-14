package pro.sky.myawesomepastebin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import pro.sky.myawesomepastebin.model.Paste;
import pro.sky.myawesomepastebin.model.enums.PasteStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.Instant;

@Data
public class PasteCreateDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String link;
    private String body;
    private String title;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant expiredTime;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    private PasteStatus status;
    public static PasteCreateDTO fromPaste(Paste paste) {
        PasteCreateDTO dto = new PasteCreateDTO();
        dto.setLink(paste.getLink());
        dto.setBody(paste.getBody());
        dto.setTitle(paste.getTitle());
        dto.setExpiredTime(paste.getExpiredTime());
        dto.setStatus(paste.getStatus());
        return dto;
    }

    public Paste toPaste() {
        Paste paste = new Paste();
        paste.setLink(this.getLink());
        paste.setBody(this.getBody());
        paste.setTitle(this.getTitle());
        paste.setExpiredTime(this.getExpiredTime());
        paste.setStatus(this.getStatus());
        return paste;
    }
}
