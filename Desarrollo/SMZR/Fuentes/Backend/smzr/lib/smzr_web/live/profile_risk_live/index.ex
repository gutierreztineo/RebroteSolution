defmodule SmzrWeb.ProfileRiskLive.Index do
  use SmzrWeb, :live_view

  alias Smzr.Monitoring
  alias Smzr.Monitoring.ProfileRisk

  @impl true
  def mount(_params, _session, socket) do
    {:ok, assign(socket, :profile_risks, list_profile_risks())}
  end

  @impl true
  def handle_params(params, _url, socket) do
    {:noreply, apply_action(socket, socket.assigns.live_action, params)}
  end

  defp apply_action(socket, :edit, %{"id" => id}) do
    socket
    |> assign(:page_title, "Edit Profile risk")
    |> assign(:profile_risk, Monitoring.get_profile_risk!(id))
  end

  defp apply_action(socket, :new, _params) do
    socket
    |> assign(:page_title, "New Profile risk")
    |> assign(:profile_risk, %ProfileRisk{})
  end

  defp apply_action(socket, :index, _params) do
    socket
    |> assign(:page_title, "Listing Profile risks")
    |> assign(:profile_risk, nil)
  end

  @impl true
  def handle_event("delete", %{"id" => id}, socket) do
    profile_risk = Monitoring.get_profile_risk!(id)
    {:ok, _} = Monitoring.delete_profile_risk(profile_risk)

    {:noreply, assign(socket, :profile_risks, list_profile_risks())}
  end

  defp list_profile_risks do
    Monitoring.list_profile_risks()
  end
end
