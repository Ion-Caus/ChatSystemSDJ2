package viewmodel;

import model.Model;

public class ViewModelFactory
{
  private ChatLoginViewModel chatLoginViewModel;
  private ChatMainViewModel chatMainViewModel;

  public ViewModelFactory(Model model)
  {
    this.chatLoginViewModel = new ChatLoginViewModel(model);
    this.chatMainViewModel = new ChatMainViewModel(model);
  }

  public ChatLoginViewModel getLoginChatViewModel()
  {
    return chatLoginViewModel;
  }

  public ChatMainViewModel getMainChatViewModel()
  {
    return chatMainViewModel;
  }
}
