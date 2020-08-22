defmodule SmzrWeb.AilmentLevelLive.Index do
  use SmzrWeb, :live_view

  alias Smzr.Monitoring
  alias Smzr.Monitoring.AilmentLevel

  @impl true
  def mount(_params, _session, socket) do
    {:ok, assign(socket, :ailment_levels, list_ailment_levels())}
  end

  @impl true
  def handle_params(params, _url, socket) do
    {:noreply, apply_action(socket, socket.assigns.live_action, params)}
  end

  defp apply_action(socket, :edit, %{"id" => id}) do
    socket
    |> assign(:page_title, "Edit Ailment level")
    |> assign(:ailment_level, Monitoring.get_ailment_level!(id))
  end

  defp apply_action(socket, :new, _params) do
    socket
    |> assign(:page_title, "New Ailment level")
    |> assign(:ailment_level, %AilmentLevel{})
  end

  defp apply_action(socket, :index, _params) do
    socket
    |> assign(:page_title, "Listing Ailment levels")
    |> assign(:ailment_level, nil)
  end

  @impl true
  def handle_event("delete", %{"id" => id}, socket) do
    ailment_level = Monitoring.get_ailment_level!(id)
    {:ok, _} = Monitoring.delete_ailment_level(ailment_level)

    {:noreply, assign(socket, :ailment_levels, list_ailment_levels())}
  end

  defp list_ailment_levels do
    Monitoring.list_ailment_levels()
  end
end
