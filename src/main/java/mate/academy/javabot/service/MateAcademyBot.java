package mate.academy.javabot.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class MateAcademyBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "http://t.me/botik_menko_bot";
    }

    @Override
    public String getBotToken() {
        return "  ";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message messageFromUser = update.getMessage();
        System.out.println("Меседж " + messageFromUser.getText());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Не знаю такого слова");
        sendMessage.setChatId(messageFromUser.getChatId());

        if (messageFromUser.getText().equals("/start")) {
            String startMessage = "";
            startMessage = startMessage + "Привіт друже!!! Просто тицьни, який в тебе " +
                    "зараз прийом харчів, та я надішлю тобі варіанти що можна попоїсти";
            sendMessage.enableMarkdown(true);
            ReplyKeyboardMarkup replyKeyboardMarkup = getKeyBoard();
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            sendMessage.setText(startMessage);
        }

        if (messageFromUser.getText().equals("Сніданок") || messageFromUser.getText().equals("сніданок")) {
            String menuBreakfast = "";
            menuBreakfast = menuBreakfast + "Можеш поїсти кашку!\n";
            menuBreakfast = menuBreakfast + "Або кашку\n";
            menuBreakfast = menuBreakfast + "Або, наприклад, вівсянку\n";
            menuBreakfast = menuBreakfast + "Або, якщо не хочеш кашку, манку\n";

            sendMessage.setText(menuBreakfast);
        }

        if (messageFromUser.getText().equals("Обід") || messageFromUser.getText().equals("обід")) {
            String menuLunch = "";
            menuLunch = menuLunch + "Тут вже треба нормально поїсти\n";
            menuLunch = menuLunch + "Можеш поїсти кашки\n";
            menuLunch = menuLunch + "Якогось м'яса\n";
            menuLunch = menuLunch + "Нуууу добре, і цукерку також з'їж\n";

            sendMessage.setText(menuLunch);
        }

        if (messageFromUser.getText().equals("Вечеря") || messageFromUser.getText().equals("вечеря")) {
            String menuDinner = "";
            menuDinner = menuDinner + "Просто доїж те, що лишилось в холодильнику, бо якщо пропаде, то буде біда\n";
            menuDinner = menuDinner + "Але їж не дуже багато\n";
            menuDinner = menuDinner + "Спробуй завершити вечерю за 2 години до сну\n";
            menuDinner = menuDinner + "Також не забудь почистити зуби\n";

            sendMessage.setText(menuDinner);
        }

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private ReplyKeyboardMarkup getKeyBoard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("Сніданок");
        keyboardRow.add("Обід");
        keyboardRow.add("Вечеря");
        keyboardRows.add(keyboardRow);
        replyKeyboardMarkup.setKeyboard(keyboardRows);

        return replyKeyboardMarkup;
    }
}
