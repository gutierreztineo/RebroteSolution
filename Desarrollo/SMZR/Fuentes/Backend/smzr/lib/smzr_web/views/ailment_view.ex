defmodule SmzrWeb.AilmentView do
  use SmzrWeb, :view
  alias SmzrWeb.AilmentView

  def render("index.json", %{ailments: ailments}) do
    %{data: render_many(ailments, AilmentView, "ailment.json")}
  end

  def render("show.json", %{ailment: ailment}) do
    %{data: render_one(ailment, AilmentView, "ailment.json")}
  end

  def render("ailment.json", %{ailment: ailment}) do
    %{id: ailment.id,
      description: ailment.description}
  end
end
