defmodule SmzrWeb.AdviceControllerTest do
  use SmzrWeb.ConnCase

  alias Smzr.Monitoring
  alias Smzr.Monitoring.Advice

  @create_attrs %{
    description: "some description"
  }
  @update_attrs %{
    description: "some updated description"
  }
  @invalid_attrs %{description: nil}

  def fixture(:advice) do
    {:ok, advice} = Monitoring.create_advice(@create_attrs)
    advice
  end

  setup %{conn: conn} do
    {:ok, conn: put_req_header(conn, "accept", "application/json")}
  end

  describe "index" do
    test "lists all advices", %{conn: conn} do
      conn = get(conn, Routes.advice_path(conn, :index))
      assert json_response(conn, 200)["data"] == []
    end
  end

  describe "create advice" do
    test "renders advice when data is valid", %{conn: conn} do
      conn = post(conn, Routes.advice_path(conn, :create), advice: @create_attrs)
      assert %{"id" => id} = json_response(conn, 201)["data"]

      conn = get(conn, Routes.advice_path(conn, :show, id))

      assert %{
               "id" => id,
               "description" => "some description"
             } = json_response(conn, 200)["data"]
    end

    test "renders errors when data is invalid", %{conn: conn} do
      conn = post(conn, Routes.advice_path(conn, :create), advice: @invalid_attrs)
      assert json_response(conn, 422)["errors"] != %{}
    end
  end

  describe "update advice" do
    setup [:create_advice]

    test "renders advice when data is valid", %{conn: conn, advice: %Advice{id: id} = advice} do
      conn = put(conn, Routes.advice_path(conn, :update, advice), advice: @update_attrs)
      assert %{"id" => ^id} = json_response(conn, 200)["data"]

      conn = get(conn, Routes.advice_path(conn, :show, id))

      assert %{
               "id" => id,
               "description" => "some updated description"
             } = json_response(conn, 200)["data"]
    end

    test "renders errors when data is invalid", %{conn: conn, advice: advice} do
      conn = put(conn, Routes.advice_path(conn, :update, advice), advice: @invalid_attrs)
      assert json_response(conn, 422)["errors"] != %{}
    end
  end

  describe "delete advice" do
    setup [:create_advice]

    test "deletes chosen advice", %{conn: conn, advice: advice} do
      conn = delete(conn, Routes.advice_path(conn, :delete, advice))
      assert response(conn, 204)

      assert_error_sent 404, fn ->
        get(conn, Routes.advice_path(conn, :show, advice))
      end
    end
  end

  defp create_advice(_) do
    advice = fixture(:advice)
    %{advice: advice}
  end
end
