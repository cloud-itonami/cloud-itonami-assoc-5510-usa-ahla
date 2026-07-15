(ns association.facts
  "Industry rule catalog for the American Hotel & Lodging Association
  (AHLA, Wikidata Q19872202) -- an 18th industry-association-level
  source (see cloud-itonami-assoc-6419-jpn-zenginkyo, -6512-jpn-sonpo,
  -6612-jpn-jsda, -6419-deu-bankenverband, -6612-usa-finra,
  -6512-usa-naic, -6920-jpn-jicpa, -6920-usa-aicpa, -6419-fra-fbf,
  -6511-jpn-seiho, -6910-jpn-nichibenren, -6810-jpn-recaj, -6411-jpn-boj,
  -6120-usa-ctia, -5110-usa-a4a, -3510-usa-eei, -2910-deu-vda for the
  first seventeen) per ADR-2607141700 (cloud-itonami-compliance-fact-federation).
  The FIRST entry aligned to ISIC 5510 (short term accommodation
  activities / hotels and similar) -- a new industry code for this
  family. A rule not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url.

  The 5-Star Promise ('Individual AHLA Member Commitments to Advance
  Safety and Security') PDF was verified by directly reading its
  rendered text via the Read tool: the document's own text states it
  was announced 2018-09-06 and this particular version was last updated
  2019-10-04. The About AHLA page was directly WebFetch-verified; the
  1910 founding (as the American Hotel Protective Association, later
  renamed) is WebSearch/Wikipedia-corroborated, not stated on the About
  page itself.")

(def catalog
  "assoc-slug -> vector of self-regulatory rule entries."
  {"ahla"
   [{:association-rule/id "ahla.5-star-promise"
     :association-rule/title "5-Star Promise: Individual AHLA Member Commitments to Advance Safety and Security"
     :association-rule/association "ahla"
     :association-rule/isic "5510"
     :association-rule/country "USA"
     :association-rule/kind :self-regulatory-code
     :association-rule/url "https://www.ahla.com/sites/default/files/five_star_promise_commitments_10-7-19_0.pdf"
     :association-rule/url-provenance :official-association-site
     :association-rule/established-date "2018-09-06"
     :association-rule/last-revised-date "2019-10-04"
     :association-rule/retrieved-at "2026-07-15"
     :association-rule/topic #{:worker-safety :sexual-harassment-prevention}}
    {:association-rule/id "ahla.about-profile"
     :association-rule/title "About AHLA (organization profile)"
     :association-rule/association "ahla"
     :association-rule/isic "5510"
     :association-rule/country "USA"
     :association-rule/kind :governance-program
     :association-rule/url "https://www.ahla.com/about"
     :association-rule/url-provenance :official-association-site
     :association-rule/established-date "1910"
     :association-rule/retrieved-at "2026-07-15"
     :association-rule/topic #{:governance}}]})

(defn spec-basis [assoc-slug] (get catalog assoc-slug))

(defn coverage
  ([] (coverage (keys catalog)))
  ([slugs]
   (let [have (filter catalog slugs)
         missing (remove catalog slugs)]
     {:requested (count slugs)
      :covered (count have)
      :covered-associations (vec (sort have))
      :missing-associations (vec (sort missing))
      :note (str "cloud-itonami-assoc-5510-usa-ahla Wave 0 (ADR-2607141700): "
                 (count (get catalog "ahla")) " ahla entries seeded with an "
                 "official ahla.com citation. Extend "
                 "`association.facts/catalog`, never fabricate a rule id/url.")})))

(defn by-topic [assoc-slug topic]
  (filterv #(contains? (:association-rule/topic %) topic) (spec-basis assoc-slug)))
