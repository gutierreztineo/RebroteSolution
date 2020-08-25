defmodule SmzrWeb.AdviceLive.FormComponent do
  use SmzrWeb, :live_component

  alias Smzr.Monitoring

  @impl true
  def update(%{advice: advice} = assigns, socket) do
    changeset = Monitoring.change_advice(advice)

    {:ok,
     socket
     |> assign(assigns)
     |> assign(:changeset, changeset)}
  end

  @impl true
  def handle_event("validate", %{"advice" => advice_params}, socket) do
    changeset =
      socket.assigns.advice
      |> Monitoring.change_advice(advice_params)
      |> Map.put(:action, :validate)

    {:noreply, assign(socket, :changeset, changeset)}
  end

  def handle_event("save", %{"advice" => advice_params}, socket) do
    save_advice(socket, socket.assigns.action, advice_params)
  end

  defp save_advice(socket, :edit, advice_params) do
    case Monitoring.update_advice(socket.assigns.advice, advice_params) do
      {:ok, _advice} ->
        {:noreply,
         socket
         |> put_flash(:info, "Advice updated successfully")
         |> push_redirect(to: socket.assigns.return_to)}

      {:error, %Ecto.Changeset{} = changeset} ->
        {:noreply, assign(socket, :changeset, changeset)}
    end
  end

  defp save_advice(socket, :new, advice_params) do
    case Monitoring.create_advice(advice_params) do
      {:ok, _advice} ->
        {:noreply,
         socket
         |> put_flash(:info, "Advice created successfully")
         |> push_redirect(to: socket.assigns.return_to)}

      {:error, %Ecto.Changeset{} = changeset} ->
        {:noreply, assign(socket, changeset: changeset)}
    end
  end
end
