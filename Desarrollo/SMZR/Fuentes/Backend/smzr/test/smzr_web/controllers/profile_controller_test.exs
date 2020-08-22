defmodule SmzrWeb.ProfileControllerTest do
  use SmzrWeb.ConnCase

  alias Smzr.Accounts
  alias Smzr.Accounts.Profile

  @create_attrs %{
    birthdate: ~D[2010-04-17],
    dni: "some dni",
    email: "some email",
    firstname: "some firstname",
    lastnamem: "some lastnamem",
    lastnamep: "some lastnamep"
  }
  @update_attrs %{
    birthdate: ~D[2011-05-18],
    dni: "some updated dni",
    email: "some updated email",
    firstname: "some updated firstname",
    lastnamem: "some updated lastnamem",
    lastnamep: "some updated lastnamep"
  }
  @invalid_attrs %{birthdate: nil, dni: nil, email: nil, firstname: nil, lastnamem: nil, lastnamep: nil}

  def fixture(:profile) do
    {:ok, profile} = Accounts.create_profile(@create_attrs)
    profile
  end

  setup %{conn: conn} do
    {:ok, conn: put_req_header(conn, "accept", "application/json")}
  end

  describe "index" do
    test "lists all profiles", %{conn: conn} do
      conn = get(conn, Routes.profile_path(conn, :index))
      assert json_response(conn, 200)["data"] == []
    end
  end

  describe "create profile" do
    test "renders profile when data is valid", %{conn: conn} do
      conn = post(conn, Routes.profile_path(conn, :create), profile: @create_attrs)
      assert %{"id" => id} = json_response(conn, 201)["data"]

      conn = get(conn, Routes.profile_path(conn, :show, id))

      assert %{
               "id" => id,
               "birthdate" => "2010-04-17",
               "dni" => "some dni",
               "email" => "some email",
               "firstname" => "some firstname",
               "lastnamem" => "some lastnamem",
               "lastnamep" => "some lastnamep"
             } = json_response(conn, 200)["data"]
    end

    test "renders errors when data is invalid", %{conn: conn} do
      conn = post(conn, Routes.profile_path(conn, :create), profile: @invalid_attrs)
      assert json_response(conn, 422)["errors"] != %{}
    end
  end

  describe "update profile" do
    setup [:create_profile]

    test "renders profile when data is valid", %{conn: conn, profile: %Profile{id: id} = profile} do
      conn = put(conn, Routes.profile_path(conn, :update, profile), profile: @update_attrs)
      assert %{"id" => ^id} = json_response(conn, 200)["data"]

      conn = get(conn, Routes.profile_path(conn, :show, id))

      assert %{
               "id" => id,
               "birthdate" => "2011-05-18",
               "dni" => "some updated dni",
               "email" => "some updated email",
               "firstname" => "some updated firstname",
               "lastnamem" => "some updated lastnamem",
               "lastnamep" => "some updated lastnamep"
             } = json_response(conn, 200)["data"]
    end

    test "renders errors when data is invalid", %{conn: conn, profile: profile} do
      conn = put(conn, Routes.profile_path(conn, :update, profile), profile: @invalid_attrs)
      assert json_response(conn, 422)["errors"] != %{}
    end
  end

  describe "delete profile" do
    setup [:create_profile]

    test "deletes chosen profile", %{conn: conn, profile: profile} do
      conn = delete(conn, Routes.profile_path(conn, :delete, profile))
      assert response(conn, 204)

      assert_error_sent 404, fn ->
        get(conn, Routes.profile_path(conn, :show, profile))
      end
    end
  end

  defp create_profile(_) do
    profile = fixture(:profile)
    %{profile: profile}
  end
end
