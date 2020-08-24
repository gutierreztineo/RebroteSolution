defmodule SmzrWeb.ProfileAilmentLive.FormComponent do
  use SmzrWeb, :live_component

  alias Smzr.Monitoring

  @impl true
  def update(%{profile_ailment: profile_ailment} = assigns, socket) do
    changeset = Monitoring.change_profile_ailment(profile_ailment)

    {:ok,
     socket
     |> assign(assigns)
     |> assign(:changeset, changeset)}
  end

  @impl true
  def handle_event("validate", %{"profile_ailment" => profile_ailment_params}, socket) do
    changeset =
      socket.assigns.profile_ailment
      |> Monitoring.change_profile_ailment(profile_ailment_params)
      |> Map.put(:action, :validate)

    {:noreply, assign(socket, :changeset, changeset)}
  end

  def handle_event("save", %{"profile_ailment" => profile_ailment_params}, socket) do
    save_profile_ailment(socket, socket.assigns.action, profile_ailment_params)
  end

  defp save_profile_ailment(socket, :edit, profile_ailment_params) do
    case Monitoring.update_profile_ailment(socket.assigns.profile_ailment, profile_ailment_params) do
      {:ok, _profile_ailment} ->
        {:noreply,
         socket
         |> put_flash(:info, "Profile ailment updated successfully")
         |> push_redirect(to: socket.assigns.return_to)}

      {:error, %Ecto.Changeset{} = changeset} ->
        {:noreply, assign(socket, :changeset, changeset)}
    end
  end

  defp save_profile_ailment(socket, :new, profile_ailment_params) do
    case Monitoring.create_profile_ailment(profile_ailment_params) do
      {:ok, _profile_ailment} ->
        {:noreply,
         socket
         |> put_flash(:info, "Profile ailment created successfully")
         |> push_redirect(to: socket.assigns.return_to)}

      {:error, %Ecto.Changeset{} = changeset} ->
        {:noreply, assign(socket, changeset: changeset)}
    end
  end
end
