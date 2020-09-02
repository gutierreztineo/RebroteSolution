defmodule Smzr.Email do
  import Bamboo.Email
  use Bamboo.Phoenix, view: SmzrWeb.EmailView


  def welcome_email(recipient) do
    base_email
    |> to(recipient)
    |> subject("Welcome!")
    |> text_body("Welcome to the app")
  end

  defp base_email do
    new_email
    |> from("tineo@makinap.com")
    |> put_header("Reply-To", "tineo@makinap.com")
  end

  def send_code(recipient) do
    base_email
    |> to(recipient)
    |> subject("SMZR - Codigo")
    |> assign(:code, "1234")
    |> render("send_code.html")
  end

end