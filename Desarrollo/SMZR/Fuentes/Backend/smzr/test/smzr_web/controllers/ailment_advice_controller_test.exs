defmodule SmzrWeb.AilmentAdviceControllerTest do
  use SmzrWeb.ConnCase

  alias Smzr.Monitoring
  alias Smzr.Monitoring.AilmentAdvice

  @create_attrs %{

  }
  @update_attrs %{

  }
  @invalid_attrs %{}

  def fixture(:ailment_advice) do
    {:ok, ailment_advice} = Monitoring.create_ailment_advice(@create_attrs)
    ailment_advice
  end

  setup %{conn: conn} do
    {:ok, conn: put_req_header(conn, "accept", "application/json")}
  end

  describe "index" do
    test "lists all ailment_advices", %{conn: conn} do
      conn = get(conn, Routes.ailment_advice_path(conn, :index))
      assert json_response(conn, 200)["data"] == []
    end
  end

  describe "create ailment_advice" do
    test "renders ailment_advice when data is valid", %{conn: conn} do
      conn = post(conn, Routes.ailment_advice_path(conn, :create), ailment_advice: @create_attrs)
      assert %{"id" => id} = json_response(conn, 201)["data"]

      conn = get(conn, Routes.ailment_advice_path(conn, :show, id))

      assert %{
               "id" => id
             } = json_response(conn, 200)["data"]
    end

    test "renders errors when data is invalid", %{conn: conn} do
      conn = post(conn, Routes.ailment_advice_path(conn, :create), ailment_advice: @invalid_attrs)
      assert json_response(conn, 422)["errors"] != %{}
    end
  end

  describe "update ailment_advice" do
    setup [:create_ailment_advice]

    test "renders ailment_advice when data is valid", %{conn: conn, ailment_advice: %AilmentAdvice{id: id} = ailment_advice} do
      conn = put(conn, Routes.ailment_advice_path(conn, :update, ailment_advice), ailment_advice: @update_attrs)
      assert %{"id" => ^id} = json_response(conn, 200)["data"]

      conn = get(conn, Routes.ailment_advice_path(conn, :show, id))

      assert %{
               "id" => id
             } = json_response(conn, 200)["data"]
    end

    test "renders errors when data is invalid", %{conn: conn, ailment_advice: ailment_advice} do
      conn = put(conn, Routes.ailment_advice_path(conn, :update, ailment_advice), ailment_advice: @invalid_attrs)
      assert json_response(conn, 422)["errors"] != %{}
    end
  end

  describe "delete ailment_advice" do
    setup [:create_ailment_advice]

    test "deletes chosen ailment_advice", %{conn: conn, ailment_advice: ailment_advice} do
      conn = delete(conn, Routes.ailment_advice_path(conn, :delete, ailment_advice))
      assert response(conn, 204)

      assert_error_sent 404, fn ->
        get(conn, Routes.ailment_advice_path(conn, :show, ailment_advice))
      end
    end
  end

  defp create_ailment_advice(_) do
    ailment_advice = fixture(:ailment_advice)
    %{ailment_advice: ailment_advice}
  end
end
