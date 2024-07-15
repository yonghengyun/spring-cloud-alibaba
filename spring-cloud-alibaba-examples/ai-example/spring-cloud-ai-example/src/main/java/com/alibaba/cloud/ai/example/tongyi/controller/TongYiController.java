/**
 * TongYi AI Controller, providing various AI service interfaces.
 */
@RestController
@RequestMapping("/ai")
@CrossOrigin
public class TongYiController {
    
    /**
     * Simple service interface for TongYi, used for general completion tasks.
     */
    @Autowired
    @Qualifier("tongYiSimpleServiceImpl")
    private TongYiService tongYiSimpleService;

    /**
     * Provides a completion function based on the input message.
     * @param message The input message, default is "Tell me a joke".
     * @return The completion result as a string.
     */
    @GetMapping("/example")
    public String completion(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return tongYiSimpleService.completion(message);
    }

    /**
     * Provides a real-time completion function based on the input message.
     * @param message The input message, default is "请告诉我西红柿炖牛腩怎么做？".
     * @return A map containing completion results.
     */
    @GetMapping("/stream")
    public Map<String, String> streamCompletion(@RequestParam(value = "message", defaultValue = "请告诉我西红柿炖牛腩怎么做？") String message) {
        return tongYiSimpleService.streamCompletion(message);
    }

    /**
     * Service interface for TongYi's output parsing, used for generating specific model outputs.
     */
    @Autowired
    @Qualifier("tongYiOutputParseServiceImpl")
    private TongYiService tongYiOutputService;

    /**
     * Generates output based on the specified actor.
     * @param actor The name of the actor, default is "Jeff Bridges".
     * @return The generated output information.
     */
    @GetMapping("/output")
    public ActorsFilms generate(@RequestParam(value = "actor", defaultValue = "Jeff Bridges") String actor) {
        return tongYiOutputService.genOutputParse(actor);
    }

    /**
     * Service interface for TongYi's prompt template, used for generating prompt templates based on adjectives and topics.
     */
    @Autowired
    @Qualifier("tongYiPromptTemplateServiceImpl")
    private TongYiService tongYiPromptTemplateService;

    /**
     * Generates prompt templates based on the specified adjective and topic.
     * @param adjective The adjective, default is "funny".
     * @param topic The topic, default is "cows".
     * @return The generated prompt message.
     */
    @GetMapping("/prompt-tmpl")
    public AssistantMessage completion(@RequestParam(value = "adjective", defaultValue = "funny") String adjective,
                                       @RequestParam(value = "topic", defaultValue = "cows") String topic) {
        return tongYiPromptTemplateService.genPromptTemplates(adjective, topic);
    }

    /**
     * Service interface for TongYi's role generation, used for generating role-playing content.
     */
    @Autowired
    @Qualifier("tongYiRolesServiceImpl")
    private TongYiService tongYiRolesService;

    /**
     * Generates role-playing content based on the specified message, name, and voice.
     * @param message The input message, default is about famous pirates.
     * @param name The name of the speaker, default is "bot".
     * @param voice The voice type, default is "pirate".
     * @return The generated role-playing message.
     */
    @GetMapping("/roles")
    public AssistantMessage generate(@RequestParam(value = "message", defaultValue = "Tell me about three famous pirates from the Golden Age of Piracy and why they did.  Write at least a sentence for each pirate.") String message,
                                    @RequestParam(value = "name", defaultValue = "bot") String name,
                                    @RequestParam(value = "voice", defaultValue = "pirate") String voice) {
        return tongYiRolesService.genRole(message, name, voice);
    }

    /**
     * Service interface for TongYi's stuff completion, used for completing content with specific information.
     */
    @Autowired
    @Qualifier("tongYiStuffServiceImpl")
    private TongYiService tongYiStuffService;

    /**
     * Completes the specified message with additional information if needed.
     * @param message The input message, default is about the 2022 Winter Olympics curling mixed doubles gold medalists.
     * @param stuffit A boolean indicating whether to add additional information, default is false.
     * @return The completion result.
     */
    @GetMapping("/stuff")
    public Completion completion(@RequestParam(value = "message",
            defaultValue = "Which athletes won the mixed doubles gold medal in curling at the 2022 Winter Olympics?") String message,
            @RequestParam(value = "stuffit", defaultValue = "false") boolean stuffit) {
        return tongYiStuffService.stuffCompletion(message, stuffit);
    }

    /**
     * Service interface for TongYi's image generation, used for generating images based on prompts.
     */
    @Autowired
    @Qualifier("tongYiImagesServiceImpl")
    private TongYiService tongYiImgService;

    /**
     * Generates an image based on the specified prompt.
     * @param imgPrompt The prompt for the image, default is "Painting a picture of blue water and blue sky."
     * @return The generated image response.
     */
    @GetMapping("/img")
    public ImageResponse genImg(@RequestParam(value = "prompt",
            defaultValue = "Painting a picture of blue water and blue sky.") String imgPrompt) {
        return tongYiImgService.genImg(imgPrompt);
    }

    /**
     * Service interface for TongYi's simple audio generation, used for generating audio content.
     */
    @Autowired
    @Qualifier("tongYiAudioSimpleServiceImpl")
    private TongYiService tongYiAudioService;

    /**
     * Generates audio content based on the specified prompt.
     * @param prompt The prompt for the audio, default is "你好，Spring Cloud Alibaba AI 框架！"
     * @return The generated audio as a string representation.
     */
@GetMapping("/音频/语音")
    公众的线热瑙迪奥(@RequestParam(值="提示",
默认值="你好，春云阿里巴巴AI框架!")线提示) {
        返回通约迪奥服务。热瑙迪奥(提示);
    }

    /**
*依桐音频转录服务接口，用于将音频内容转录为文本。
     */
@自动连线
@限定符(" tonyaudiotranscriptionserviceimpl ")
    私人的统一服务统一音频转录服务;

    /**
*将指定URL中的音频内容转录为文本。
* @param url音频的url，默认是一个样本音频文件。
* @返回转录的文本结果。
     */
    /**
*音频转录。支持URL音频资源。
* {@link Resource}
* {@link转录参数}
* @param url音频url。
* @返回转录结果，是字符串类型。
     */
@GetMapping("/音频/转录")
    公众的线音频转录(@RequestParam(值="音频网址",
默认值=" https://dash scope . OSS-cn-Beijing . aliyuncs . com/samples/audio/para former/real time _ ASR _ example . wav ")线全球资源定位器(Uniform Resource Locator)) {
        返回tongYiAudioTranscriptionService。音频转录(全球资源定位器(Uniform Resource Locator));
    }

    /**
*依桐文本嵌入的服务接口，用于生成文本嵌入。
     */
@自动连线
@限定符(" tongYiTextEmbeddingServiceImpl ")
    私人的统一服务tongYiTextEmbeddingService;

    /**
*为指定文本生成嵌入内容。
* @param text输入文本，默认为"春云阿里巴巴AI框架!"。
* @返回表示文本嵌入的双精度值列表。
     */
@GetMapping("/textEmbedding ")
    公众的列表< Double >文本嵌入(@RequestParam(值="文本",
默认值=“春云阿里巴巴AI框架!")线文本) {
        返回tongYiTextEmbeddingService。文本嵌入(文本);
    }

			   }
