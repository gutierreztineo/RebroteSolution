defmodule SmzrWeb.UserLocationView do
  use SmzrWeb, :view
  alias SmzrWeb.UserLocationView

  def render("index.json", %{user_locations: user_locations}) do
    %{data: render_many(user_locations, UserLocationView, "user_location.json")}
  end

  def render("show.json", %{user_location: user_location}) do
    %{data: render_one(user_location, UserLocationView, "user_location.json")}
  end

  def render("user_location.json", %{user_location: user_location}) do
    %{id: user_location.id}
  end
end
