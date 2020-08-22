defmodule SmzrWeb.ProfileView do
  use SmzrWeb, :view
  alias SmzrWeb.ProfileView

  def render("index.json", %{profiles: profiles}) do
    %{data: render_many(profiles, ProfileView, "profile.json")}
  end

  def render("show.json", %{profile: profile}) do
    %{data: render_one(profile, ProfileView, "profile.json")}
  end

  def render("profile.json", %{profile: profile}) do
    %{id: profile.id,
      firstname: profile.firstname,
      lastnamep: profile.lastnamep,
      lastnamem: profile.lastnamem,
      dni: profile.dni,
      birthdate: profile.birthdate,
      email: profile.email}
  end
end
