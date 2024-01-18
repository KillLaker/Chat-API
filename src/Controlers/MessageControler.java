package Controlers;

import Service.MessageService;

public class MessageControler {
    private MessageService _messageService;

    public MessageControler(MessageService _messageService){
        this._messageService = _messageService;
    }

    public void sendMessage(String content){
        this._messageService.sendMessage(content);
    }

    public void deleteMessage(String content){
        this._messageService.deleteMessage(content);
    }

    public void editMessage(String content, String newContent){
        this._messageService.editMessage(content, newContent);
    }
}
