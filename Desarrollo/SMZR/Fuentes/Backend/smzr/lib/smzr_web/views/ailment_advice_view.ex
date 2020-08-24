defmodule SmzrWeb.AilmentAdviceView do
  use SmzrWeb, :view
  alias SmzrWeb.AilmentAdviceView

  def render("index.json", %{ailment_advices: ailment_advices}) do
    %{data: render_many(ailment_advices, AilmentAdviceView, "ailment_advice.json")}
  end

  def render("show.json", %{ailment_advice: ailment_advice}) do
    %{data: render_one(ailment_advice, AilmentAdviceView, "ailment_advice.json")}
  end

  def render("ailment_advice.json", %{ailment_advice: ailment_advice}) do
    %{id: ailment_advice.id}
  end
end
