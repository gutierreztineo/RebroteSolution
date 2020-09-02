defmodule SmzrWeb.AilmentLevelLive.FormComponent do
  use SmzrWeb, :live_component
  alias Smzr.Monitoring

  @impl true
  def update(%{ailment_level: ailment_level} = assigns, socket) do
    changeset = Monitoring.change_ailment_level(ailment_level)

    {:ok,
     socket
     |> assign(assigns)
     |> assign(:changeset, changeset)}
  end

  @impl true
  def handle_event("validate", %{"ailment_level" => ailment_level_params}, socket) do
    changeset =
      socket.assigns.ailment_level
      |> Monitoring.change_ailment_level(ailment_level_params)
      |> Map.put(:action, :validate)

    {:noreply, assign(socket, :changeset, changeset)}
  end

  def handle_event("save", %{"ailment_level" => ailment_level_params}, socket) do
    save_ailment_level(socket, socket.assigns.action, ailment_level_params)
  end

  defp save_ailment_level(socket, :edit, ailment_level_params) do
    case Monitoring.update_ailment_level(socket.assigns.ailment_level, ailment_level_params) do
      {:ok, _ailment_level} ->
        {:noreply,
         socket
         |> put_flash(:info, "Ailment level updated successfully")
         |> push_redirect(to: socket.assigns.return_to)}

      {:error, %Ecto.Changeset{} = changeset} ->
        {:noreply, assign(socket, :changeset, changeset)}
    end
  end

  defp save_ailment_level(socket, :new, ailment_level_params) do
    case Monitoring.create_ailment_level(ailment_level_params) do
      {:ok, _ailment_level} ->
        {:noreply,
         socket
         |> put_flash(:info, "Ailment level created successfully")
         |> push_redirect(to: socket.assigns.return_to)}

      {:error, %Ecto.Changeset{} = changeset} ->
        {:noreply, assign(socket, changeset: changeset)}
    end
  end
end
