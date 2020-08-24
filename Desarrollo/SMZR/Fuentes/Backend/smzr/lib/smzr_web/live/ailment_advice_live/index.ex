defmodule SmzrWeb.AilmentAdviceLive.Index do
  use SmzrWeb, :live_view

  alias Smzr.Monitoring
  alias Smzr.Monitoring.AilmentAdvice

  @impl true
  def mount(_params, _session, socket) do
    {:ok, assign(socket, :ailment_advices, list_ailment_advices())}
  end

  @impl true
  def handle_params(params, _url, socket) do
    {:noreply, apply_action(socket, socket.assigns.live_action, params)}
  end

  defp apply_action(socket, :edit, %{"id" => id}) do
    socket
    |> assign(:page_title, "Edit Ailment advice")
    |> assign(:ailment_advice, Monitoring.get_ailment_advice!(id))
  end

  defp apply_action(socket, :new, _params) do
    socket
    |> assign(:page_title, "New Ailment advice")
    |> assign(:ailment_advice, %AilmentAdvice{})
  end

  defp apply_action(socket, :index, _params) do
    socket
    |> assign(:page_title, "Listing Ailment advices")
    |> assign(:ailment_advice, nil)
  end

  @impl true
  def handle_event("delete", %{"id" => id}, socket) do
    ailment_advice = Monitoring.get_ailment_advice!(id)
    {:ok, _} = Monitoring.delete_ailment_advice(ailment_advice)

    {:noreply, assign(socket, :ailment_advices, list_ailment_advices())}
  end

  defp list_ailment_advices do
    Monitoring.list_ailment_advices()
  end
end
