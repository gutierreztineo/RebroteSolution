defmodule SmzrWeb.AilmentLevelView do
  use SmzrWeb, :view
  alias SmzrWeb.AilmentLevelView

  def render("index.json", %{ailment_levels: ailment_levels}) do
    %{data: render_many(ailment_levels, AilmentLevelView, "ailment_level.json")}
  end

  def render("show.json", %{ailment_level: ailment_level}) do
    %{data: render_one(ailment_level, AilmentLevelView, "ailment_level.json")}
  end

  def render("ailment_level.json", %{ailment_level: ailment_level}) do
    %{id: ailment_level.id,
      description: ailment_level.description,
      level: ailment_level.level}
  end
end
