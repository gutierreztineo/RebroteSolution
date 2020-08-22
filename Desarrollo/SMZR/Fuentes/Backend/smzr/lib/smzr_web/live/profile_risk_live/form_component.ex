defmodule SmzrWeb.ProfileRiskLive.FormComponent do
  use SmzrWeb, :live_component

  alias Smzr.Monitoring

  @impl true
  def update(%{profile_risk: profile_risk} = assigns, socket) do
    changeset = Monitoring.change_profile_risk(profile_risk)

    {:ok,
     socket
     |> assign(assigns)
     |> assign(:changeset, changeset)}
  end

  @impl true
  def handle_event("validate", %{"profile_risk" => profile_risk_params}, socket) do
    changeset =
      socket.assigns.profile_risk
      |> Monitoring.change_profile_risk(profile_risk_params)
      |> Map.put(:action, :validate)

    {:noreply, assign(socket, :changeset, changeset)}
  end

  def handle_event("save", %{"profile_risk" => profile_risk_params}, socket) do
    save_profile_risk(socket, socket.assigns.action, profile_risk_params)
  end

  defp save_profile_risk(socket, :edit, profile_risk_params) do
    case Monitoring.update_profile_risk(socket.assigns.profile_risk, profile_risk_params) do
      {:ok, _profile_risk} ->
        {:noreply,
         socket
         |> put_flash(:info, "Profile risk updated successfully")
         |> push_redirect(to: socket.assigns.return_to)}

      {:error, %Ecto.Changeset{} = changeset} ->
        {:noreply, assign(socket, :changeset, changeset)}
    end
  end

  defp save_profile_risk(socket, :new, profile_risk_params) do
    case Monitoring.create_profile_risk(profile_risk_params) do
      {:ok, _profile_risk} ->
        {:noreply,
         socket
         |> put_flash(:info, "Profile risk created successfully")
         |> push_redirect(to: socket.assigns.return_to)}

      {:error, %Ecto.Changeset{} = changeset} ->
        {:noreply, assign(socket, changeset: changeset)}
    end
  end
end
