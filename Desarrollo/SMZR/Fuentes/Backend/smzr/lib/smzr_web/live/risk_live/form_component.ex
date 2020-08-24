defmodule SmzrWeb.RiskLive.FormComponent do
  use SmzrWeb, :live_component

  alias Smzr.Monitoring

  @impl true
  def update(%{risk: risk} = assigns, socket) do
    changeset = Monitoring.change_risk(risk)

    {:ok,
     socket
     |> assign(assigns)
     |> assign(:changeset, changeset)}
  end

  @impl true
  def handle_event("validate", %{"risk" => risk_params}, socket) do
    changeset =
      socket.assigns.risk
      |> Monitoring.change_risk(risk_params)
      |> Map.put(:action, :validate)

    {:noreply, assign(socket, :changeset, changeset)}
  end

  def handle_event("save", %{"risk" => risk_params}, socket) do
    save_risk(socket, socket.assigns.action, risk_params)
  end

  defp save_risk(socket, :edit, risk_params) do
    case Monitoring.update_risk(socket.assigns.risk, risk_params) do
      {:ok, _risk} ->
        {:noreply,
         socket
         |> put_flash(:info, "Risk updated successfully")
         |> push_redirect(to: socket.assigns.return_to)}

      {:error, %Ecto.Changeset{} = changeset} ->
        {:noreply, assign(socket, :changeset, changeset)}
    end
  end

  defp save_risk(socket, :new, risk_params) do
    case Monitoring.create_risk(risk_params) do
      {:ok, _risk} ->
        {:noreply,
         socket
         |> put_flash(:info, "Risk created successfully")
         |> push_redirect(to: socket.assigns.return_to)}

      {:error, %Ecto.Changeset{} = changeset} ->
        {:noreply, assign(socket, changeset: changeset)}
    end
  end
end
