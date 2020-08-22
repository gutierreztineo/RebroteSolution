defmodule SmzrWeb.UserLocationLive.Index do
  use SmzrWeb, :live_view

  alias Smzr.Tracking
  alias Smzr.Tracking.UserLocation

  @impl true
  def mount(_params, _session, socket) do
    {:ok, assign(socket, :user_locations, list_user_locations())}
  end

  @impl true
  def handle_params(params, _url, socket) do
    {:noreply, apply_action(socket, socket.assigns.live_action, params)}
  end

  defp apply_action(socket, :edit, %{"id" => id}) do
    socket
    |> assign(:page_title, "Edit User location")
    |> assign(:user_location, Tracking.get_user_location!(id))
  end

  defp apply_action(socket, :new, _params) do
    socket
    |> assign(:page_title, "New User location")
    |> assign(:user_location, %UserLocation{})
  end

  defp apply_action(socket, :index, _params) do
    socket
    |> assign(:page_title, "Listing User locations")
    |> assign(:user_location, nil)
  end

  @impl true
  def handle_event("delete", %{"id" => id}, socket) do
    user_location = Tracking.get_user_location!(id)
    {:ok, _} = Tracking.delete_user_location(user_location)

    {:noreply, assign(socket, :user_locations, list_user_locations())}
  end

  defp list_user_locations do
    Tracking.list_user_locations()
  end
end
