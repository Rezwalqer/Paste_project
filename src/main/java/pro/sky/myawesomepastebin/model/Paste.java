package pro.sky.myawesomepastebin.model;

import lombok.Data;
import pro.sky.myawesomepastebin.model.enums.PasteStatus;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@Data
public class Paste {
    @Id
    private String link;
    private String title;
    private String body;
    @Enumerated(EnumType.STRING)
    private PasteStatus status;
    private Instant expiredTime;
    private Instant creationTime = Instant.now();

}
