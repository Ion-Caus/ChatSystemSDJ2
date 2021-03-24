package viewmodel;

import model.Model;

public class ViewModelFactory
{
  private ChatLogViewModel chatLogViewModel;

  public ViewModelFactory(Model model){
    this.chatLogViewModel = new ChatLogViewModel(model);
  }

  public ChatLogViewModel getChatLogViewModel()
  {
    return chatLogViewModel;
  }
}
