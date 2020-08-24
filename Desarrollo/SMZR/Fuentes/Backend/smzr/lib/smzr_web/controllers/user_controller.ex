defmodule SmzrWeb.UserController do
  use SmzrWeb, :controller

  alias Smzr.Accounts
  alias Smzr.Accounts.User
  alias Smzr.Guardian

  action_fallback SmzrWeb.FallbackController

  def index(conn, _params) do
    users = Accounts.list_users()
    render(conn, "index.json", users: users)
  end

  def create(conn, %{"user" => user_params}) do
    with {:ok, %User{} = user} <- Accounts.create_user(user_params) do
      conn
      |> put_status(:created)
      |> put_resp_header("location", Routes.user_path(conn, :show, user))
      |> render("show.json", user: user)
    end
  end

  def show(conn, %{"id" => id}) do
    user = Accounts.get_user!(id)
    render(conn, "show.json", user: user)
  end

  def exists_user(conn, %{"username" => username}) do
    IO.inspect(Accounts.get_user_by_username(username))
    if Accounts.get_user_by_username(username) do
      conn
      |> put_status(:ok)
      |> json(%{data: true})
      else
      conn
      |> put_status(:ok)
      |> json(%{data: false})
    end

  end

  def update(conn, %{"id" => id, "user" => user_params}) do
    user = Accounts.get_user!(id)

    with {:ok, %User{} = user} <- Accounts.update_user(user, user_params) do
      render(conn, "show.json", user: user)
    end
  end

  def delete(conn, %{"id" => id}) do
    user = Accounts.get_user!(id)

    with {:ok, %User{}} <- Accounts.delete_user(user) do
      send_resp(conn, :no_content, "")
    end
  end

  def sign_in(conn, %{"username" => username, "password" => password}) do
    case Smzr.Accounts.authenticate_user(username, password) do
      {:ok, user} ->
        conn
        |> put_session(:current_user_id, user.id)
        |> configure_session(renew: true)
        |> put_status(:ok)
        |> put_view(SmzrWeb.UserView)
        |> render("sign_in.json", user: user)

      {:error, message} ->
        conn
        |> delete_session(:current_user_id)
        |> put_status(:unauthorized)
        |> put_view(SmzrWeb.ErrorView)
        |> render("401.json", message: message)
    end
  end

  def show_jwt(conn, _params) do
    user = Guardian.Plug.current_resource(conn)
    render(conn, "show.json", user: user)
  end

  def create_jwt(conn, %{"user" => user_params}) do
    with {:ok, %User{} = user} <- Accounts.create_user(user_params) do
      {:ok, token, _claims} = Guardian.encode_and_sign(user)
      conn |> render("jwt.json", token: token)
    end
  end

  def sign_in_jwt(conn, %{"username" => username, "password" => password}) do
    #with {:ok, token, _claims} <- Accounts.token_sign_in(username, password) do
    #  conn |> render("jwt.json", token: token)
    #end

    case Accounts.token_sign_in(username, password)  do
      {:ok, token, _claims} ->
        conn |> render("jwt.json", token: token)
      {:error, message} ->
        conn
        |> put_status(:unauthorized)
        |> put_view(SmzrWeb.ErrorView)
        |> render("401.json", message: message)
    end
  end

  def test_mail(conn, _params) do
    Smzr.Email.welcome_email("cesar.gutierrez15@unmsm.edu.pe") |> Smzr.Mailer.deliver_later()

    {:stop,
      conn
      |> redirect(to: "/")}
  end

end
