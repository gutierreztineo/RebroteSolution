defmodule SmzrWeb.AilmentLive.FormComponent do
  use SmzrWeb, :live_component

  alias Smzr.Monitoring

  @impl true
  def update(%{ailment: ailment} = assigns, socket) do
    changeset = Monitoring.change_ailment(ailment)

    {:ok,
     socket
     |> assign(assigns)
     |> assign(:changeset, changeset)}
  end

  @impl true
  def handle_event("validate", %{"ailment" => ailment_params}, socket) do
    changeset =
      socket.assigns.ailment
      |> Monitoring.change_ailment(ailment_params)
      |> Map.put(:action, :validate)

    {:noreply, assign(socket, :changeset, changeset)}
  end

  def handle_event("save", %{"ailment" => ailment_params}, socket) do
    save_ailment(socket, socket.assigns.action, ailment_params)
  end

  defp save_ailment(socket, :edit, ailment_params) do
    case Monitoring.update_ailment(socket.assigns.ailment, ailment_params) do
      {:ok, _ailment} ->
        {:noreply,
         socket
         |> put_flash(:info, "Ailment updated successfully")
         |> push_redirect(to: socket.assigns.return_to)}

      {:error, %Ecto.Changeset{} = changeset} ->
        {:noreply, assign(socket, :changeset, changeset)}
    end
  end

  defp save_ailment(socket, :new, ailment_params) do
    case Monitoring.create_ailment(ailment_params) do
      {:ok, _ailment} ->
        {:noreply,
         socket
         |> put_flash(:info, "Ailment created successfully")
         |> push_redirect(to: socket.assigns.return_to)}

      {:error, %Ecto.Changeset{} = changeset} ->
        {:noreply, assign(socket, changeset: changeset)}
    end
  end
end
