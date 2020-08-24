defmodule SmzrWeb.RiskLive.Show do
  use SmzrWeb, :live_view

  alias Smzr.Monitoring

  @impl true
  def mount(_params, _session, socket) do
    {:ok, socket}
  end

  @impl true
  def handle_params(%{"id" => id}, _, socket) do
    {:noreply,
     socket
     |> assign(:page_title, page_title(socket.assigns.live_action))
     |> assign(:risk, Monitoring.get_risk!(id))}
  end

  defp page_title(:show), do: "Show Risk"
  defp page_title(:edit), do: "Edit Risk"
end
