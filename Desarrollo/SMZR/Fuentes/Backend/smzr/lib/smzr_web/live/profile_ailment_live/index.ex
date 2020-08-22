defmodule SmzrWeb.ProfileAilmentLive.Index do
  use SmzrWeb, :live_view

  alias Smzr.Monitoring
  alias Smzr.Monitoring.ProfileAilment

  @impl true
  def mount(_params, _session, socket) do
    {:ok, assign(socket, :profile_ailments, list_profile_ailments())}
  end

  @impl true
  def handle_params(params, _url, socket) do
    {:noreply, apply_action(socket, socket.assigns.live_action, params)}
  end

  defp apply_action(socket, :edit, %{"id" => id}) do
    socket
    |> assign(:page_title, "Edit Profile ailment")
    |> assign(:profile_ailment, Monitoring.get_profile_ailment!(id))
  end

  defp apply_action(socket, :new, _params) do
    socket
    |> assign(:page_title, "New Profile ailment")
    |> assign(:profile_ailment, %ProfileAilment{})
  end

  defp apply_action(socket, :index, _params) do
    socket
    |> assign(:page_title, "Listing Profile ailments")
    |> assign(:profile_ailment, nil)
  end

  @impl true
  def handle_event("delete", %{"id" => id}, socket) do
    profile_ailment = Monitoring.get_profile_ailment!(id)
    {:ok, _} = Monitoring.delete_profile_ailment(profile_ailment)

    {:noreply, assign(socket, :profile_ailments, list_profile_ailments())}
  end

  defp list_profile_ailments do
    Monitoring.list_profile_ailments()
  end
end
