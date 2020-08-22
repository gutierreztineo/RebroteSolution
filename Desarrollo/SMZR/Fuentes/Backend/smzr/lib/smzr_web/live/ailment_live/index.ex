defmodule SmzrWeb.AilmentLive.Index do
  use SmzrWeb, :live_view

  alias Smzr.Monitoring
  alias Smzr.Monitoring.Ailment

  @impl true
  def mount(_params, _session, socket) do
    {:ok, assign(socket, :ailments, list_ailments())}
  end

  @impl true
  def handle_params(params, _url, socket) do
    {:noreply, apply_action(socket, socket.assigns.live_action, params)}
  end

  defp apply_action(socket, :edit, %{"id" => id}) do
    socket
    |> assign(:page_title, "Edit Ailment")
    |> assign(:ailment, Monitoring.get_ailment!(id))
  end

  defp apply_action(socket, :new, _params) do
    socket
    |> assign(:page_title, "New Ailment")
    |> assign(:ailment, %Ailment{})
  end

  defp apply_action(socket, :index, _params) do
    socket
    |> assign(:page_title, "Listing Ailments")
    |> assign(:ailment, nil)
  end

  @impl true
  def handle_event("delete", %{"id" => id}, socket) do
    ailment = Monitoring.get_ailment!(id)
    {:ok, _} = Monitoring.delete_ailment(ailment)

    {:noreply, assign(socket, :ailments, list_ailments())}
  end

  defp list_ailments do
    Monitoring.list_ailments()
  end
end
