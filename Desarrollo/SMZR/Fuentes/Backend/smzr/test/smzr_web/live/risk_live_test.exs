defmodule SmzrWeb.RiskLiveTest do
  use SmzrWeb.ConnCase

  import Phoenix.LiveViewTest

  alias Smzr.Monitoring

  @create_attrs %{descripcion: "some descripcion", name: "some name"}
  @update_attrs %{descripcion: "some updated descripcion", name: "some updated name"}
  @invalid_attrs %{descripcion: nil, name: nil}

  defp fixture(:risk) do
    {:ok, risk} = Monitoring.create_risk(@create_attrs)
    risk
  end

  defp create_risk(_) do
    risk = fixture(:risk)
    %{risk: risk}
  end

  describe "Index" do
    setup [:create_risk]

    test "lists all risks", %{conn: conn, risk: risk} do
      {:ok, _index_live, html} = live(conn, Routes.risk_index_path(conn, :index))

      assert html =~ "Listing Risks"
      assert html =~ risk.descripcion
    end

    test "saves new risk", %{conn: conn} do
      {:ok, index_live, _html} = live(conn, Routes.risk_index_path(conn, :index))

      assert index_live |> element("a", "New Risk") |> render_click() =~
               "New Risk"

      assert_patch(index_live, Routes.risk_index_path(conn, :new))

      assert index_live
             |> form("#risk-form", risk: @invalid_attrs)
             |> render_change() =~ "can&apos;t be blank"

      {:ok, _, html} =
        index_live
        |> form("#risk-form", risk: @create_attrs)
        |> render_submit()
        |> follow_redirect(conn, Routes.risk_index_path(conn, :index))

      assert html =~ "Risk created successfully"
      assert html =~ "some descripcion"
    end

    test "updates risk in listing", %{conn: conn, risk: risk} do
      {:ok, index_live, _html} = live(conn, Routes.risk_index_path(conn, :index))

      assert index_live |> element("#risk-#{risk.id} a", "Edit") |> render_click() =~
               "Edit Risk"

      assert_patch(index_live, Routes.risk_index_path(conn, :edit, risk))

      assert index_live
             |> form("#risk-form", risk: @invalid_attrs)
             |> render_change() =~ "can&apos;t be blank"

      {:ok, _, html} =
        index_live
        |> form("#risk-form", risk: @update_attrs)
        |> render_submit()
        |> follow_redirect(conn, Routes.risk_index_path(conn, :index))

      assert html =~ "Risk updated successfully"
      assert html =~ "some updated descripcion"
    end

    test "deletes risk in listing", %{conn: conn, risk: risk} do
      {:ok, index_live, _html} = live(conn, Routes.risk_index_path(conn, :index))

      assert index_live |> element("#risk-#{risk.id} a", "Delete") |> render_click()
      refute has_element?(index_live, "#risk-#{risk.id}")
    end
  end

  describe "Show" do
    setup [:create_risk]

    test "displays risk", %{conn: conn, risk: risk} do
      {:ok, _show_live, html} = live(conn, Routes.risk_show_path(conn, :show, risk))

      assert html =~ "Show Risk"
      assert html =~ risk.descripcion
    end

    test "updates risk within modal", %{conn: conn, risk: risk} do
      {:ok, show_live, _html} = live(conn, Routes.risk_show_path(conn, :show, risk))

      assert show_live |> element("a", "Edit") |> render_click() =~
               "Edit Risk"

      assert_patch(show_live, Routes.risk_show_path(conn, :edit, risk))

      assert show_live
             |> form("#risk-form", risk: @invalid_attrs)
             |> render_change() =~ "can&apos;t be blank"

      {:ok, _, html} =
        show_live
        |> form("#risk-form", risk: @update_attrs)
        |> render_submit()
        |> follow_redirect(conn, Routes.risk_show_path(conn, :show, risk))

      assert html =~ "Risk updated successfully"
      assert html =~ "some updated descripcion"
    end
  end
end
