defmodule SmzrWeb.UserLocationLive.FormComponent do
  use SmzrWeb, :live_component

  alias Smzr.Tracking

  @impl true
  def update(%{user_location: user_location} = assigns, socket) do
    changeset = Tracking.change_user_location(user_location)

    {:ok,
     socket
     |> assign(assigns)
     |> assign(:changeset, changeset)}
  end

  @impl true
  def handle_event("validate", %{"user_location" => user_location_params}, socket) do
    changeset =
      socket.assigns.user_location
      |> Tracking.change_user_location(user_location_params)
      |> Map.put(:action, :validate)

    {:noreply, assign(socket, :changeset, changeset)}
  end

  def handle_event("save", %{"user_location" => user_location_params}, socket) do
    save_user_location(socket, socket.assigns.action, user_location_params)
  end

  defp save_user_location(socket, :edit, user_location_params) do
    case Tracking.update_user_location(socket.assigns.user_location, user_location_params) do
      {:ok, _user_location} ->
        {:noreply,
         socket
         |> put_flash(:info, "User location updated successfully")
         |> push_redirect(to: socket.assigns.return_to)}

      {:error, %Ecto.Changeset{} = changeset} ->
        {:noreply, assign(socket, :changeset, changeset)}
    end
  end

  defp save_user_location(socket, :new, user_location_params) do
    case Tracking.create_user_location(user_location_params) do
      {:ok, _user_location} ->
        {:noreply,
         socket
         |> put_flash(:info, "User location created successfully")
         |> push_redirect(to: socket.assigns.return_to)}

      {:error, %Ecto.Changeset{} = changeset} ->
        {:noreply, assign(socket, changeset: changeset)}
    end
  end
end
