defmodule SmzrWeb.AdviceView do
  use SmzrWeb, :view
  alias SmzrWeb.AdviceView

  def render("index.json", %{advices: advices}) do
    %{data: render_many(advices, AdviceView, "advice.json")}
  end

  def render("show.json", %{advice: advice}) do
    %{data: render_one(advice, AdviceView, "advice.json")}
  end

  def render("advice.json", %{advice: advice}) do
    %{id: advice.id,
      description: advice.description}
  end
end
