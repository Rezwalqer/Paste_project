package pro.sky.myawesomepastebin.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pro.sky.myawesomepastebin.dto.PasteCheck;
import pro.sky.myawesomepastebin.dto.PasteCreateDTO;
import pro.sky.myawesomepastebin.model.Paste;
import pro.sky.myawesomepastebin.model.enums.ExpirationTime;
import pro.sky.myawesomepastebin.model.enums.PasteStatus;
import pro.sky.myawesomepastebin.repository.PasteRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static pro.sky.myawesomepastebin.repository.specification.PasteSpecification.byBody;
import static pro.sky.myawesomepastebin.repository.specification.PasteSpecification.byTitle;

@Service
public class PasteService {
    private final PasteRepository pasteRepository;

    public PasteService(PasteRepository pasteRepository) {
        this.pasteRepository = pasteRepository;
    }

    public String createPaste(PasteCreateDTO pasteDTO, ExpirationTime time, PasteStatus pasteStatus) {
        Paste paste = pasteDTO.toPaste();
        paste.setLink(UUID.randomUUID().toString());
        paste.setExpiredTime(Instant.now().plus(time.getDuration()));
        paste.setStatus(pasteStatus);
        pasteRepository.save(paste);
        return paste.getLink();
    }
    public List<PasteCheck> findAllPublicPaste() {
        return pasteRepository.findAllByStatusPublic()
                .stream()
                .map(PasteCheck::fromPaste)
                .collect(Collectors.toList());
    }
    public PasteCheck findByLink(String link) {
        return PasteCheck.fromPaste(pasteRepository.findAllByLinkLike(link));
    }
    public List<PasteCheck> findByTitleOrBody(String title, String body) {
        return pasteRepository.findAll(Specification.where(byTitle(title).and(byBody(body))))
                .stream()
                .map(PasteCheck::fromPaste)
                .collect(Collectors.toList());
    }
}
