defmodule SmzrWeb.UserLocationLive.Show do
  use SmzrWeb, :live_view

  alias Smzr.Tracking

  @impl true
  def mount(_params, _session, socket) do
    {:ok, socket}
  end

  @impl true
  def handle_params(%{"id" => id}, _, socket) do
    {:noreply,
     socket
     |> assign(:page_title, page_title(socket.assigns.live_action))
     |> assign(:user_location, Tracking.get_user_location!(id))}
  end

  defp page_title(:show), do: "Show User location"
  defp page_title(:edit), do: "Edit User location"
end
