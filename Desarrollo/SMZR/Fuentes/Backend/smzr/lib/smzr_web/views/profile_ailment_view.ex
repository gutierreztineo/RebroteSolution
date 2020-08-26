defmodule SmzrWeb.ProfileAilmentView do
  use SmzrWeb, :view
  alias SmzrWeb.ProfileAilmentView

  def render("index.json", %{profile_ailments: profile_ailments}) do
    %{data: render_many(profile_ailments, ProfileAilmentView, "profile_ailment.json")}
  end

  def render("show.json", %{profile_ailment: profile_ailment}) do
    %{data: render_one(profile_ailment, ProfileAilmentView, "profile_ailment.json")}
  end

  def render("profile_ailment.json", %{profile_ailment: profile_ailment}) do
    %{
      id: profile_ailment.id,
      profile_id: profile_ailment.profile_id,
      ailment_levels_id: profile_ailment.ailment_levels_id
    }
  end
end
