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
    @GetMapping("/audio/speech")
    public String genAudio(@RequestParam(value = "prompt",
            defaultValue = "你好，Spring Cloud Alibaba AI 框架！") String prompt) {
        return tongYiAudioService.genAudio(prompt);
    }

    /**
     * Service interface for TongYi's audio transcription, used for transcribing audio content into text.
     */
    @Autowired
    @Qualifier("tongYiAudioTranscriptionServiceImpl")
    private TongYiService tongYiAudioTranscriptionService;

    /**
     * Transcribes the audio content from the specified URL into text.
     * @param url The URL of the audio, default is a sample audio file.
     * @return The transcribed text result.
     */
    /**
     * audio transcription. Support urls audio resource.
     * {@link Resource}
     * {@link TranscriptionParam}
     * @param url audio url.
     * @return transcription result, is String type.
     */
    private final TongYiAudioTranscriptionService tongYiAudioTranscriptionService;

    public AudioTranscriptionController(TongYiAudioTranscriptionService tongYiAudioTranscriptionService) {
        this.tongYiAudioTranscriptionService = tongYiAudioTranscriptionService;
    }

    @GetMapping("/audio/transcription")
    @ResponseStatus(HttpStatus.OK)
    public String audioTranscription(@RequestParam(
        value = "audioUrls",
        defaultValue = "https://dashscope.oss-cn-beijing.aliyuncs.com/samples/audio/paraformer/realtime_asr_example.wav"
    ) String url) {
        // 参数校验
        if (!isValidUrl(url)) {
            throw new IllegalArgumentException("Invalid URL provided.");
        }

        try {
            return tongYiAudioTranscriptionService.audioTranscription(url);
        } catch (Exception e) {
            // 异常处理逻辑
            // 此处可以根据具体情况记录日志、发送告警等
            System.err.println("Failed to transcribe audio: " + e.getMessage());
            throw new RuntimeException("Failed to process audio transcription.", e);
        }
    }

    private boolean isValidUrl(String url) {
        // URL校验逻辑，可以使用正则表达式、第三方库等进行实现
        // 此处仅为示例，实际应用中可能需要更严格的校验
        String regex = "https?://.+.wav";
        return url.matches(regex);
    }

    /**
     * Service interface for TongYi's text embedding, used for generating embeddings for text.
     */
    @Autowired
    @Qualifier("tongYiTextEmbeddingServiceImpl")
    private TongYiService tongYiTextEmbeddingService;

    /**
     * Generates embeddings for the specified text.
     * @param text The input text, default is "Spring Cloud Alibaba AI 框架！".
     * @return A list of doubles representing the text embedding.
     */
    @GetMapping("/textEmbedding")
    public List<Double> textEmbedding(@RequestParam(value = "text",
            defaultValue = "Spring Cloud Alibaba AI 框架！") String text) {
        return tongYiTextEmbeddingService.textEmbedding(text);
    }

}
