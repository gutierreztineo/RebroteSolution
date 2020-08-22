defmodule SmzrWeb.AdviceLive.Index do
  use SmzrWeb, :live_view

  alias Smzr.Monitoring
  alias Smzr.Monitoring.Advice

  @impl true
  def mount(_params, _session, socket) do
    {:ok, assign(socket, :advices, list_advices())}
  end

  @impl true
  def handle_params(params, _url, socket) do
    {:noreply, apply_action(socket, socket.assigns.live_action, params)}
  end

  defp apply_action(socket, :edit, %{"id" => id}) do
    socket
    |> assign(:page_title, "Edit Advice")
    |> assign(:advice, Monitoring.get_advice!(id))
  end

  defp apply_action(socket, :new, _params) do
    socket
    |> assign(:page_title, "New Advice")
    |> assign(:advice, %Advice{})
  end

  defp apply_action(socket, :index, _params) do
    socket
    |> assign(:page_title, "Listing Advices")
    |> assign(:advice, nil)
  end

  @impl true
  def handle_event("delete", %{"id" => id}, socket) do
    advice = Monitoring.get_advice!(id)
    {:ok, _} = Monitoring.delete_advice(advice)

    {:noreply, assign(socket, :advices, list_advices())}
  end

  defp list_advices do
    Monitoring.list_advices()
  end
end
