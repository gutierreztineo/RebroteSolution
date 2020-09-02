defmodule SmzrWeb.AilmentLevelLive.Show do
  use SmzrWeb, :live_view

  alias Smzr.Monitoring

  @impl true
  def mount(_params, _session, socket) do
    {:ok, assign(socket, :ailments, Monitoring.list_ailments())}
  end

  @impl true
  def handle_params(%{"id" => id}, _, socket) do
    {:noreply,
     socket
     |> assign(:page_title, page_title(socket.assigns.live_action))
     |> assign(:ailment_level, Monitoring.get_ailment_level!(id))}
  end

  defp page_title(:show), do: "Show Ailment level"
  defp page_title(:edit), do: "Edit Ailment level"
end
