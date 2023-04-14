package pro.sky.myawesomepastebin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.myawesomepastebin.dto.LinkDTO;
import pro.sky.myawesomepastebin.dto.PasteCheck;
import pro.sky.myawesomepastebin.dto.PasteCreateDTO;
import pro.sky.myawesomepastebin.model.enums.ExpirationTime;
import pro.sky.myawesomepastebin.model.enums.PasteStatus;
import pro.sky.myawesomepastebin.service.PasteService;

import java.util.List;

@RestController
@RequestMapping("/my-awesome-pastebin")
public class PasteController {


    private final PasteService pasteService;

    public PasteController(PasteService pasteService) {
        this.pasteService = pasteService;
    }

    @PostMapping
    public String createPaste(
            @RequestBody PasteCreateDTO pasteDTO,
            @RequestParam ExpirationTime expirationTime,
            @RequestParam PasteStatus pasteStatus

    ) {
        return "http://my-awesome-pastebin.tld/" + pasteService.createPaste(pasteDTO, expirationTime, pasteStatus);
    }

    @GetMapping(value = "/last-ten")
    public List<PasteCheck> findAllPublic() {
        return pasteService.findAllPublicPaste();
    }

    @GetMapping(value = "/{link}")
    public PasteCheck findByLink(@PathVariable String link) {
        return pasteService.findByLink(link);
    }

    @GetMapping
    public List<PasteCheck> findByTitleOrBody(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String body) {

        return pasteService.findByTitleOrBody(title, body);

    }
}
