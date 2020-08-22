defmodule SmzrWeb.AilmentAdviceLive.FormComponent do
  use SmzrWeb, :live_component

  alias Smzr.Monitoring

  @impl true
  def update(%{ailment_advice: ailment_advice} = assigns, socket) do
    changeset = Monitoring.change_ailment_advice(ailment_advice)

    {:ok,
     socket
     |> assign(assigns)
     |> assign(:changeset, changeset)}
  end

  @impl true
  def handle_event("validate", %{"ailment_advice" => ailment_advice_params}, socket) do
    changeset =
      socket.assigns.ailment_advice
      |> Monitoring.change_ailment_advice(ailment_advice_params)
      |> Map.put(:action, :validate)

    {:noreply, assign(socket, :changeset, changeset)}
  end

  def handle_event("save", %{"ailment_advice" => ailment_advice_params}, socket) do
    save_ailment_advice(socket, socket.assigns.action, ailment_advice_params)
  end

  defp save_ailment_advice(socket, :edit, ailment_advice_params) do
    case Monitoring.update_ailment_advice(socket.assigns.ailment_advice, ailment_advice_params) do
      {:ok, _ailment_advice} ->
        {:noreply,
         socket
         |> put_flash(:info, "Ailment advice updated successfully")
         |> push_redirect(to: socket.assigns.return_to)}

      {:error, %Ecto.Changeset{} = changeset} ->
        {:noreply, assign(socket, :changeset, changeset)}
    end
  end

  defp save_ailment_advice(socket, :new, ailment_advice_params) do
    case Monitoring.create_ailment_advice(ailment_advice_params) do
      {:ok, _ailment_advice} ->
        {:noreply,
         socket
         |> put_flash(:info, "Ailment advice created successfully")
         |> push_redirect(to: socket.assigns.return_to)}

      {:error, %Ecto.Changeset{} = changeset} ->
        {:noreply, assign(socket, changeset: changeset)}
    end
  end
end
